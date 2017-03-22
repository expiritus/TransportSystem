package com.belhard.misha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<T> {

    int insert(T ob) throws SQLException, IllegalAccessException;

    void update(T ob) throws SQLException;

    void delete(Class<T> c, int id) throws SQLException;

    List<T> findAll(Class<T> c) throws SQLException;

    T findById(Class<T> c, int id) throws SQLException;

    List<T> fillListEntity(ResultSet resultSet) throws SQLException;

    T fillEntity(ResultSet resultSet) throws SQLException;

}
