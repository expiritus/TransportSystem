package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoTransport extends DaoAbstract<Transport> {

    @Override
    public int insert(Transport ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(Transport ob) throws SQLException {

    }

    @Override
    public List<Transport> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
