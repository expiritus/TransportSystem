package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.TransportType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTransportType extends DaoAbstract<TransportType> {


    @Override
    public List<TransportType> fillListEntity(ResultSet resultSet) throws SQLException {
        List<TransportType> transportTypes = new ArrayList<>();
        while (resultSet.next()) {
            TransportType transportType = new TransportType();
            transportType.setId(resultSet.getInt("id"));
            transportType.setType(resultSet.getString("type"));
            transportTypes.add(transportType);
        }
        return transportTypes;
    }


    @Override
    public TransportType fillEntity(ResultSet resultSet) throws SQLException {
        TransportType transportType = new TransportType();
        if (resultSet.next()) {
            transportType.setId(resultSet.getInt("id"));
            transportType.setType(resultSet.getString("type"));
        }
        return transportType;
    }
}
