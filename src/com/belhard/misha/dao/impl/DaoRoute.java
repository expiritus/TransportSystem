package com.belhard.misha.dao.impl;

import com.belhard.misha.db.ConnectDb;
import com.belhard.misha.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRoute extends DaoAbstract<Route> {


    @Override
    public List<Route> findAll(Class<Route> c) throws SQLException {
        String sql = "SELECT r.id, type.type, tr.model, tr.capacity, tr.speed, " +
                "fr.city AS city_from, too.city AS city_to, " +
                "st.status, r.time_departure, r.arrival_time " +
                "FROM " + getTableName(c) + " r " +
                "JOIN transport tr ON r.transport_id = tr.id " +
                "JOIN transport_type type ON tr.transport_type_id = type.id " +
                "JOIN city fr ON r.`from` = fr.id " +
                "JOIN city too ON r.`to` = too.id " +
                "JOIN status st ON r.status_id = st.id";
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = prepared.executeQuery()) {
                    return fillListEntity(resultSet);
                }
            }
        }
    }

    @Override
    public List<Route> fillListEntity(ResultSet resultSet) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while (resultSet.next()) {
            Route route = new Route();
            route.setId(resultSet.getInt("id"));

            TransportType transportType = new TransportType();
            transportType.setType(resultSet.getString("type"));
            Transport transport = new Transport();
            transport.setTransportType(transportType);
            transport.setModel(resultSet.getString("model"));
            transport.setCapacity(resultSet.getInt("capacity"));
            transport.setSpeed(resultSet.getDouble("speed"));
            route.setTransport(transport);

            City cityFom = new City();
            cityFom.setCity(resultSet.getString("city_from"));
            route.setFromCity(cityFom);

            City cityTo = new City();
            cityTo.setCity(resultSet.getString("city_to"));
            route.setToCity(cityTo);

            Status status = new Status();
            status.setStatus(resultSet.getString("status"));
            route.setStatus(status);
            route.setTimeDeparture(resultSet.getString("time_departure"));
            route.setArrivalTime(resultSet.getString("arrival_time"));
            routes.add(route);
        }
        return routes;
    }


    @Override
    public Route fillEntity(ResultSet resultSet) throws SQLException {
        Route route = new Route();
        if (resultSet.next()) {
            route.setId(resultSet.getInt("id"));

            TransportType transportType = new TransportType();
            transportType.setType(resultSet.getString("type"));
            Transport transport = new Transport();
            transport.setTransportType(transportType);
            transport.setModel(resultSet.getString("model"));
            transport.setCapacity(resultSet.getInt("capacity"));
            transport.setSpeed(resultSet.getDouble("speed"));
            route.setTransport(transport);

            City cityFom = new City();
            cityFom.setCity(resultSet.getString("city_from"));
            route.setFromCity(cityFom);

            City cityTo = new City();
            cityTo.setCity(resultSet.getString("city_to"));
            route.setToCity(cityTo);

            Status status = new Status();
            status.setStatus(resultSet.getString("status"));
            route.setStatus(status);
            route.setTimeDeparture(resultSet.getString("time_departure"));
            route.setArrivalTime(resultSet.getString("arrival_time"));
        }
        return route;
    }
}
