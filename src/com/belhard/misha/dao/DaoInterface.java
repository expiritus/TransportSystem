package com.belhard.misha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<T> {

    int insert(T ob) throws SQLException;

    void update(T ob) throws SQLException;

    void delete(T ob) throws SQLException;

    ResultSet findAll(T ob) throws SQLException;

    T findById(T ob) throws SQLException;
}
