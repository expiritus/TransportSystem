package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DaoRole extends DaoAbstract<Role> {

    @Override
    public List<Role> fillListEntity(ResultSet resultSet) throws DaoException {
        List<Role> roles = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));
                roles.add(role);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill list role entities", e);
        }
        return roles;
    }


    @Override
    public Role fillEntity(ResultSet resultSet) throws DaoException {
        Role role = new Role();
        try {
            if (resultSet.next()) {
                role.setId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill role entity", e);
        }
        return role;
    }
}
