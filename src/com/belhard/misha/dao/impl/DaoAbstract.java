package com.belhard.misha.dao.impl;

import com.belhard.misha.CustomAnnotations.IgnoreForInsert;
import com.belhard.misha.dao.DaoInterface;
import com.belhard.misha.db.ConnectDb;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DaoAbstract<T> implements DaoInterface<T> {


    @Override
    public int insert(T ob) throws SQLException {
        Field[] fieldsReflect = ob.getClass().getDeclaredFields();
        List<Field> clearEntityFields = new ArrayList<>();
        for (Field field : fieldsReflect) {
            if (!Modifier.isStatic(field.getModifiers()) && field.getAnnotation(IgnoreForInsert.class) == null) {
                clearEntityFields.add(field);
            }
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(ob.getClass().getSimpleName().toLowerCase()).append(" (");
        StringBuilder values = new StringBuilder();
        for (Field field : clearEntityFields) {
            sql.append(field.getName()).append(",");
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
                    try {
                        clearEntityFields.get(i).setAccessible(true);
                        prepared.setObject(i + 1, clearEntityFields.get(i).get(ob));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                prepared.execute();
                return lastInsertedId(prepared);
            }
        }
    }

    @Override
    public void update(T ob) throws SQLException {

    }

    @Override
    public void delete(Class<T> c, int id) throws SQLException {
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(
                    "DELETE FROM " + c.getSimpleName().toLowerCase() +
                            " WHERE id = ?"
            )) {
                prepared.setInt(1, id);
                prepared.execute();
            }
        }
    }

    @Override
    public List<T> findAll(Class<T> c) throws SQLException {
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + c.getSimpleName().toLowerCase()
            )) {
                try (ResultSet resultSet = prepared.executeQuery()) {
                    return fillListEntity(resultSet);
                }
            }
        }
    }


    @Override
    public T findById(int id, Class<T> c) throws SQLException {
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + c.getSimpleName().toLowerCase() +
                            " WHERE id = ?"
            )) {
                prepared.setInt(1, id);
                try (ResultSet resultSet = prepared.executeQuery()) {
                    return fillEntity(resultSet);
                }
            }
        }
    }

    public int lastInsertedId(PreparedStatement prepared) throws SQLException {
        int lastInserted = 0;
        ResultSet resultSet = prepared.getGeneratedKeys();
        if (resultSet.next()) {
            lastInserted = resultSet.getInt(1);
        }
        return lastInserted;
    }
}