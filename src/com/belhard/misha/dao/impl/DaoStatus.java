package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoStatus extends DaoAbstract<Status> {

    @Override
    public int insert(Status ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(Status ob) throws SQLException {

    }

    @Override
    public List<Status> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public Status findById(Status ob) throws SQLException {
        return null;
    }

    @Override
    public Status fillEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
