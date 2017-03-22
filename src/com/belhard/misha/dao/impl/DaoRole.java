package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoRole extends DaoAbstract<Role> {

    @Override
    public List<Role> fillListEntity(ResultSet resultSet) throws SQLException {
        List<Role> roles = new ArrayList<>();
        while (resultSet.next()) {
            Role role = new Role();
            role.setId(resultSet.getInt("id"));
            role.setRole(resultSet.getString("role"));
            roles.add(role);
        }
        return roles;
    }


    @Override
    public Role fillEntity(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        if (resultSet.next()) {
            role.setId(resultSet.getInt("id"));
            role.setRole(resultSet.getString("role"));
        }
        return role;
    }
}
