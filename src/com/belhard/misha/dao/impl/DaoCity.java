package com.belhard.misha.dao.impl;

import com.belhard.misha.db.ConnectDb;
import com.belhard.misha.entity.City;
import com.belhard.misha.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCity extends DaoAbstract<City> {


    @Override
    public List<City> findAll(Class<City> c) throws SQLException {
        String sql = "SELECT ci.id, ci.city, ci.country_id, k.country FROM " + getTableName(c) + " ci" +
                        " JOIN country k  ON ci.country_id = k.id ";
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillListEntity(resultSet);
                }

            }
        }
    }

    @Override
    public List<City> fillListEntity(ResultSet resultSet) throws SQLException {
        List<City> list = new ArrayList<>();
        while (resultSet.next()) {
            City city = new City();
            city.setId(resultSet.getInt("id"));
            city.setCity(resultSet.getString("city"));
            city.setCountryId(resultSet.getInt("country_id"));
            Country country = new Country();
            country.setCountry(resultSet.getString("country"));
            city.setCountry(country);
            list.add(city);
        }
        return list;
    }


    public City fillEntity(ResultSet resultSet) throws SQLException {
        City city = null;
        if (resultSet.next()) {
            city = new City();
            city.setId(resultSet.getInt("id"));
            city.setCity(resultSet.getString("city"));
            city.setCountryId(resultSet.getInt("country_id"));
            Country country = new Country();
            country.setCountry(resultSet.getString("country"));
            city.setCountry(country);
        }
        return city;
    }
}
