package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.DaoInterface;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DaoAbstract<T> implements DaoInterface<T> {

    @Override
    public void delete(T ob) throws SQLException {

    }

    @Override
    public List<T> findAll(Class<T> c) throws SQLException {
        return null;
    }

    @Override
    public T findById(T ob) throws SQLException {
        return null;
    }


    public int lastInsertedId(PreparedStatement prepared) throws SQLException{
        int lastInserted = 0;
        ResultSet resultSet = prepared.getGeneratedKeys();
        if(resultSet.next()){
            lastInserted = resultSet.getInt(1);
        }
        return lastInserted;
    }
}