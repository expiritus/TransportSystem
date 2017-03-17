package com.belhard.misha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DaoAbstract<T> implements DaoInterface<T> {

    @Override
    public void delete(T ob) throws SQLException {

    }

    @Override
    public ResultSet findAll(T ob) throws SQLException {
        return null;
    }

    @Override
    public T findById(T ob) throws SQLException {
        return null;
    }
}
