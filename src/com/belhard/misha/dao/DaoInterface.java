package com.belhard.misha.dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoInterface<T> {

    void insert(T ob) throws SQLException;

    void update(T ob) throws SQLException;

    void delete(T ob) throws SQLException;

    List<T> findAll(T ob) throws SQLException;
}
