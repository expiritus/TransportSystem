package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.db.ConnectDb;
import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.entity.Transport;
import com.belhard.misha.entity.TransportType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTransport extends DaoAbstract<Transport> {


    @Override
    public List<Transport> findAll(Class<Transport> c) throws DaoException {
        String sql = "SELECT t.id, t.transport_type_id, t.model, t.capacity, t.speed, type.type " +
                        " FROM " + getTableName(c) + " t" +
                            " JOIN transport_type type ON t.transport_type_id = type.id";
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillListEntity(resultSet);
                }
            }
        }catch (SQLException e){
            throw new DaoException("Error occurred during fill list from " + getTableName(c), e);
        }
    }

    @Override
    public List<Transport> fillListEntity(ResultSet resultSet) throws DaoException {
        List<Transport> transports = new ArrayList<>();
        try{
            while (resultSet.next()){
                Transport transport = new Transport();
                transport.setId(resultSet.getInt("id"));
                transport.setTransportTypeId(resultSet.getInt("transport_type_id"));
                transport.setModel(resultSet.getString("model"));
                transport.setCapacity(resultSet.getInt("capacity"));
                transport.setSpeed(resultSet.getDouble("speed"));
                TransportType transportType = new TransportType();
                transportType.setType(resultSet.getString("type"));
                transport.setTransportType(transportType);
                transports.add(transport);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill list transport entities", e);
        }
        return transports;
    }


    @Override
    public Transport fillEntity(ResultSet resultSet) throws DaoException {
        Transport transport = new Transport();
        try{
            if(resultSet.next()){
                transport.setId(resultSet.getInt("id"));
                transport.setTransportTypeId(resultSet.getInt("transport_type_id"));
                transport.setModel(resultSet.getString("model"));
                transport.setCapacity(resultSet.getInt("capacity"));
                transport.setSpeed(resultSet.getDouble("speed"));
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill transport entity", e);
        }
        return transport;
    }
}
