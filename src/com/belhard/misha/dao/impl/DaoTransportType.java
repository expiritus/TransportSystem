package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.entity.TransportType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTransportType extends DaoAbstract<TransportType> {


    @Override
    public List<TransportType> fillListEntity(ResultSet resultSet) throws DaoException {
        List<TransportType> transportTypes = new ArrayList<>();
        try{
            while (resultSet.next()) {
                TransportType transportType = new TransportType();
                transportType.setId(resultSet.getInt("id"));
                transportType.setType(resultSet.getString("type"));
                transportTypes.add(transportType);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill list transportType entities", e);
        }

        return transportTypes;
    }


    @Override
    public TransportType fillEntity(ResultSet resultSet) throws DaoException {
        TransportType transportType = new TransportType();
        try{
            if (resultSet.next()) {
                transportType.setId(resultSet.getInt("id"));
                transportType.setType(resultSet.getString("type"));
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill transportType entity", e);
        }

        return transportType;
    }
}
