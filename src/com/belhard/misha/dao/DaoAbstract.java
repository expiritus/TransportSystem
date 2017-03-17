package com.belhard.misha.dao;

import java.sql.SQLException;
import java.util.List;

public abstract class DaoAbstract implements DaoInterface<DaoAbstract> {

    @Override
    public void insert(DaoAbstract ob) throws SQLException {

    }

    @Override
    public void update(DaoAbstract ob) throws SQLException {

    }

    @Override
    public void delete(DaoAbstract ob) throws SQLException {

    }

    @Override
    public List<DaoAbstract> findAll(DaoAbstract ob) throws SQLException {
        return null;
    }
}
