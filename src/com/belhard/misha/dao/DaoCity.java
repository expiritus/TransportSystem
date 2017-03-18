package com.belhard.misha.dao;

import com.belhard.misha.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoCity extends DaoAbstract<City> {

    @Override
    public int insert(City ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(City ob) throws SQLException {
    }

    @Override
    public List<City> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
