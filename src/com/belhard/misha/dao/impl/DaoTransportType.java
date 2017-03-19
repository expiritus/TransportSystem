package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.TransportType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoTransportType extends DaoAbstract<TransportType> {

    @Override
    public int insert(TransportType ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(TransportType ob) throws SQLException {

    }

    @Override
    public List<TransportType> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
