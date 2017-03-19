package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoCountry extends DaoAbstract<Country> {

    @Override
    public int insert(Country ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(Country ob) throws SQLException {

    }

    @Override
    public List<Country> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
