package com.belhard.misha.dao;

import com.belhard.misha.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class DaoRole extends DaoAbstract<Role> {

    @Override
    public int insert(Role ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(Role ob) throws SQLException {

    }

    @Override
    public List<Role> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
