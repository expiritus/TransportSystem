package com.belhard.misha.dao.impl;

import com.belhard.misha.db.ConnectDb;
import com.belhard.misha.entity.*;
import com.belhard.misha.entity.Transport;
import com.belhard.misha.entity.TransportType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTicket extends DaoAbstract<Ticket> {

    @Override
    public List<Ticket> findAll(Class<Ticket> c) throws SQLException {
        String sql = "SELECT t.id, u.name, type.type, tr.model, tr.capacity, " +
                        "`from`.city AS city_from, `to`.city AS city_to, r.time_departure, r.arrival_time " +
                        "FROM " + getTableName(c) + " t JOIN user u ON t.user_id = u.id " +
                        "JOIN route r ON t.route_id = r.id " +
                        "JOIN transport tr ON r.transport_id = tr.id " +
                        "JOIN transport_type type ON tr.transport_type_id = type.id " +
                        "JOIN city `from` ON r.`from` = `from`.id " +
                        "JOIN city `to` ON r.`to` = `to`.id";
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                try(ResultSet resultSet = prepared.executeQuery()) {
                    return fillListEntity(resultSet);
                }
            }
        }
    }

    @Override
    public List<Ticket> fillListEntity(ResultSet resultSet) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()){
            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getInt("id"));

            User user = new User();
            user.setName(resultSet.getString("name"));
            ticket.setUser(user);

            Route route = new Route();
            TransportType transportType = new TransportType();
            transportType.setType(resultSet.getString("type"));

            Transport transport = new Transport();
            transport.setTransportType(transportType);
            transport.setModel(resultSet.getString("model"));
            transport.setCapacity(resultSet.getInt("capacity"));
            route.setTransport(transport);

            City cityFrom = new City();
            cityFrom.setCity(resultSet.getString("city_from"));
            route.setFromCity(cityFrom);

            City cityTo = new City();
            cityTo.setCity(resultSet.getString("city_to"));
            route.setToCity(cityTo);
            route.setTimeDeparture(resultSet.getString("time_departure"));
            route.setArrivalTime(resultSet.getString("arrival_time"));
            ticket.setRoute(route);

            tickets.add(ticket);
        }
        return tickets;
    }


    @Override
    public Ticket fillEntity(ResultSet resultSet) throws SQLException {
        Ticket ticket = new Ticket();
        if(resultSet.next()){
            ticket.setId(resultSet.getInt("id"));

            User user = new User();
            user.setName(resultSet.getString("name"));
            ticket.setUser(user);

            Route route = new Route();
            TransportType transportType = new TransportType();
            transportType.setType(resultSet.getString("type"));

            Transport transport = new Transport();
            transport.setTransportType(transportType);
            transport.setModel(resultSet.getString("model"));
            transport.setCapacity(resultSet.getInt("capacity"));
            route.setTransport(transport);

            City cityFrom = new City();
            cityFrom.setCity(resultSet.getString("city_from"));
            route.setFromCity(cityFrom);

            City cityTo = new City();
            cityTo.setCity(resultSet.getString("city_to"));
            route.setToCity(cityTo);
            route.setTimeDeparture(resultSet.getString("time_departure"));
            route.setArrivalTime(resultSet.getString("arrival_time"));
        }
        return ticket;
    }
}
