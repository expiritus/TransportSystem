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
    public int insert(City ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(City ob) throws SQLException {
    }

    @Override
    public List<City> findAll(Class<City> c) throws SQLException {
        try (Connection connection = ConnectDb.getInstance().getConnection()) {
            try (PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + c.getSimpleName().toLowerCase()
            )) {
                try (ResultSet resultSet = prepared.executeQuery()) {
                    return fillListEntity(resultSet);
                }
            }
        }
    }

    @Override
    public List<City> fillListEntity(ResultSet resultSet) throws SQLException {
        List<City> list = new ArrayList<>();
        while (resultSet.next()){
            City city = new City();
            city.setId(resultSet.getInt("id"));
            city.setCity(resultSet.getString("city"));
            list.add(city);
        }
        return list;
    }

    @Override
    public City findById(City ob) throws SQLException {
        try(Connection connection = ConnectDb.getInstance().getConnection()) {
            try(PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + ob.getClass().getSimpleName().toLowerCase() +
                            " WHERE id = ?"
            )){
                prepared.setInt(1, ob.getId());
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillEntity(resultSet);
                }
            }
        }
    }

    public City fillEntity(ResultSet resultSet) throws SQLException{
        City city = null;
        while (resultSet.next()){
            city = new City();
            Country country = new Country();
            country.setCountry(resultSet.getString("country"));
            city.setCountry(country);
        }
        return city;
    }
}
