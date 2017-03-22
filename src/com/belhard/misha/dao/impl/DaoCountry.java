package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCountry extends DaoAbstract<Country> {



    @Override
    public List<Country> fillListEntity(ResultSet resultSet) throws SQLException {
        List<Country> list = new ArrayList<>();
        while (resultSet.next()){
            Country country = new Country();
            country.setId(resultSet.getInt("id"));
            country.setCountry(resultSet.getString("country"));
            list.add(country);
        }
        return list;
    }

    @Override
    public Country fillEntity(ResultSet resultSet) throws SQLException {
        Country country = new Country();
        if (resultSet.next()){
            country.setId(resultSet.getInt("id"));
            country.setCountry(resultSet.getString("country"));
        }
        return country;
    }


}
