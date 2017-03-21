package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoRoute extends DaoAbstract<Route> {

    @Override
    public int insert(Route ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(Route ob) throws SQLException {

    }

    @Override
    public List<Route> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Route findById(Route ob) throws SQLException {
        return null;
    }

    @Override
    public Route fillEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
