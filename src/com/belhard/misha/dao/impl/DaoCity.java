package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.db.ConnectDb;
import com.belhard.misha.dao.exceptions.DaoException;
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
    public List<City> findAll(Class<City> c) throws DaoException {
        String sql = "SELECT ci.id, ci.city, ci.country_id, k.country FROM " + getTableName(c) + " ci" +
                        " JOIN country k  ON ci.country_id = k.id ";
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillListEntity(resultSet);
                }

            }
        }catch (SQLException e){
            throw new DaoException("Can not find objects from " + getTableName(c), e);
        }
    }


    @Override
    public List<City> fillListEntity(ResultSet resultSet) throws DaoException {
        List<City> list = new ArrayList<>();
        try {
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
        }catch (SQLException e){
            throw new DaoException("Can not fill list with city entities", e);
        }
        return list;
    }

    @Override
    public City findById(Class<City> c, int id) throws DaoException {
        String sql = "SELECT ci.id, ci.city, ci.country_id, k.country FROM " + getTableName(c) + " ci" +
                " JOIN country k  ON ci.country_id = k.id " +
                " WHERE ci.id = " + id;
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillEntity(resultSet);
                }
            }
        }catch (SQLException e){
            throw new DaoException("Can not find city by id = " + id);
        }
    }

    public City fillEntity(ResultSet resultSet) throws DaoException {
        City city = null;
        try{
            if (resultSet.next()) {
                city = new City();
                city.setId(resultSet.getInt("id"));
                city.setCity(resultSet.getString("city"));
                city.setCountryId(resultSet.getInt("country_id"));
                Country country = new Country();
                country.setCountry(resultSet.getString("country"));
                city.setCountry(country);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill city entity", e);
        }
        return city;
    }
}
