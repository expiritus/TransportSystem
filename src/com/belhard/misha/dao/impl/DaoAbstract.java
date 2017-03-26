package com.belhard.misha.dao.impl;

import com.belhard.misha.customAnnotations.ClassMapping;
import com.belhard.misha.customAnnotations.FieldMapping;
import com.belhard.misha.customAnnotations.IgnoreForInsert;
import com.belhard.misha.dao.DaoInterface;
import com.belhard.misha.dao.db.ConnectDb;
import com.belhard.misha.dao.exceptions.DaoException;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DaoAbstract<T> implements DaoInterface<T> {


    @Override
    public int insert(T ob) throws DaoException {
        Field[] fieldsReflect = ob.getClass().getDeclaredFields();
        List<Field> clearEntityFields = new ArrayList<>();
        for (Field field : fieldsReflect) {
            if (field.getAnnotation(IgnoreForInsert.class) == null) {
                clearEntityFields.add(field);
            }
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(ob.getClass().getAnnotation(ClassMapping.class).name().toLowerCase()).append(" (");
        StringBuilder values = new StringBuilder();
        for (Field field : clearEntityFields) {
            String name = (field.getAnnotation(FieldMapping.class) != null &&
                    !field.getAnnotation(FieldMapping.class).name().isEmpty()) ?
                    field.getAnnotation(FieldMapping.class).name() :
                    field.getName();
            sql.append("`").append(name).append("`").append(",");
            values.append("?,");
        }
        sql.setLength(sql.length() - 1);

        sql.append(") VALUES (");
        sql.append(values);

        sql.setLength(sql.length() - 1);
        sql.append(")");

        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(sql.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {
                for (int i = 0; i < clearEntityFields.size(); i++) {
                    clearEntityFields.get(i).setAccessible(true);
                    try {
                        prepared.setObject(i + 1, clearEntityFields.get(i).get(ob));
                    } catch (IllegalAccessException e) {
                        throw new DaoException("Can not get reflection field", e);
                    }
                }

                prepared.execute();
                return lastInsertedId(prepared);
            }
        }catch (SQLException e){
            throw new DaoException("Can not insert object", e);
        }
    }


    @Override
    public void update(T ob) throws DaoException {

    }

    @Override
    public void delete(Class<T> c, int id) throws DaoException {
        String sql = "DELETE FROM " + getTableName(c) + " WHERE id = ?";
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(sql)) {
                prepared.setInt(1, id);
                prepared.execute();
            }
        }catch (SQLException e){
            throw new DaoException("Can not delete from " + getTableName(c) + " with id = " + id, e);
        }
    }

    @Override
    public List<T> findAll(Class<T> c) throws DaoException {
        String sql = "SELECT * FROM " + getTableName(c);
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = prepared.executeQuery()) {
                    return fillListEntity(resultSet);
                }
            }
        }catch (SQLException e){
            throw new DaoException("Can not find " + getTableName(c), e);
        }
    }


    @Override
    public T findById(Class<T> c, int id) throws DaoException {
        String sql = "SELECT * FROM " + getTableName(c) + " WHERE id = ?";
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(sql)) {
                prepared.setInt(1, id);
                try (ResultSet resultSet = prepared.executeQuery()) {
                    return fillEntity(resultSet);
                }
            }
        }catch (SQLException e){
            throw new DaoException("Can not find object in " + getTableName(c) + " with id = " + id, e);
        }
    }

    public int lastInsertedId(PreparedStatement prepared) throws DaoException {
        int lastInserted = 0;
        try {
            ResultSet resultSet = prepared.getGeneratedKeys();
            if (resultSet.next()) {
                lastInserted = resultSet.getInt(1);
            }
        }catch (SQLException e){
            throw new DaoException("Can not get id last inserted object", e);
        }
        return lastInserted;
    }

    protected String getTableName(Class<T> c){
        return (c.getAnnotation(ClassMapping.class) != null &&
                !c.getAnnotation(ClassMapping.class).name().isEmpty()) ?
                c.getAnnotation(ClassMapping.class).name() :
                c.getName().toLowerCase();
    }
}