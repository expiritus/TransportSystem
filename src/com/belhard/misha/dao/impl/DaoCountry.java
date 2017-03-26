package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCountry extends DaoAbstract<Country> {



    @Override
    public List<Country> fillListEntity(ResultSet resultSet) throws DaoException {
        List<Country> list = new ArrayList<>();
        try {
            while (resultSet.next()){
                Country country = new Country();
                country.setId(resultSet.getInt("id"));
                country.setCountry(resultSet.getString("country"));
                list.add(country);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill list country entities", e);
        }
        return list;
    }

    @Override
    public Country fillEntity(ResultSet resultSet) throws DaoException {
        Country country = new Country();
        try {
            if (resultSet.next()){
                country.setId(resultSet.getInt("id"));
                country.setCountry(resultSet.getString("country"));
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill country entity", e);
        }
        return country;
    }


}
