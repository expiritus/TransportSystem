package com.belhard.misha.dao;

import com.belhard.misha.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoTicket extends DaoAbstract<Ticket> {

    @Override
    public int insert(Ticket ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(Ticket ob) throws SQLException {

    }

    @Override
    public List<Ticket> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}