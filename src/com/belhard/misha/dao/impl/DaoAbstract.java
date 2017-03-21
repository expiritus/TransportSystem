package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.DaoInterface;
import com.belhard.misha.db.ConnectDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DaoAbstract<T> implements DaoInterface<T> {

    @Override
    public void delete(T ob) throws SQLException {

    }

    @Override
    public List<T> findAll(Class<T> c) throws SQLException {
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + c.getSimpleName().toLowerCase()
            )){
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillListEntity(resultSet);
                }
            }
        }
    }



    @Override
    public T findById(T ob) throws SQLException {
        try(Connection connection = ConnectDb.getInstance().getConnection()) {
            try(PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + ob.getClass().getSimpleName().toLowerCase() +
                            " WHERE id = ?"
            )) {
                prepared.setInt(1, Integer.parseInt(ob.getClass().getDeclaredField("id").toString()));
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillEntity(resultSet);
                }
            }catch (NoSuchFieldException e){
                throw new RuntimeException(e);
            }
        }
    }


    public int lastInsertedId(PreparedStatement prepared) throws SQLException{
        int lastInserted = 0;
        ResultSet resultSet = prepared.getGeneratedKeys();
        if(resultSet.next()){
            lastInserted = resultSet.getInt(1);
        }
        return lastInserted;
    }

    public List<String> getMetaData(Class<T> c){
        return null;
    }
}