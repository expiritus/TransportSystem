package com.belhard.misha.dao;

import com.belhard.misha.dao.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<T> {

    int insert(T ob) throws SQLException, IllegalAccessException;

    void update(T ob, int id) throws DaoException;

    void delete(Class<T> c, int id) throws DaoException;

    List<T> findAll(Class<T> c) throws DaoException;

    T findById(Class<T> c, int id) throws DaoException;

    List<T> fillListEntity(ResultSet resultSet) throws DaoException;

    T fillEntity(ResultSet resultSet) throws DaoException;

}
