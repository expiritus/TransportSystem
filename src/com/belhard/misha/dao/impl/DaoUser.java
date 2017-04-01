package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.db.ConnectDb;
import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUser extends DaoAbstract<User> {

    public void assignRoleUser(User ob) throws DaoException{
        String sql = "INSERT INTO user_to_role (id_user, id_role) " +
                        "VALUES (?, (SELECT id FROM role WHERE role='user'))";
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                prepared.setInt(1, ob.getId());
                prepared.execute();
            }
        }catch (SQLException e){
            throw new DaoException("Can not assign role to user", e);
        }
    }

    public void assignRolesUser(int idUser, int idRole) throws DaoException{
        String sql = "INSERT INTO user_to_role (id_user, id_role) " +
                        "VALUES (?, ?)";
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                prepared.setInt(1, idUser);
                prepared.setInt(2, idRole);
                prepared.execute();
            }
        }catch (SQLException e){
            throw new DaoException("Can not assign roles to user", e);
        }
    }


    public void deleteRolesUser(int idUser) throws DaoException{
        String sql = "DELETE FROM user_to_role WHERE id_user = " + idUser;
        try(Connection connection = ConnectDb.getInstance().getConnection()) {
            try(PreparedStatement prepared = connection.prepareStatement(sql)){
                prepared.execute();
            }
        }catch (SQLException e){
            throw new DaoException("Can not delete from user_to_role id_user " + idUser, e);
        }
    }

    public User findByLoginAndPassword(User ob) throws DaoException{
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + ob.getClass().getSimpleName().toLowerCase() +
                        " WHERE login = ? AND password = ?"
            )) {
                prepared.setString(1, ob.getLogin());
                prepared.setString(2, ob.getPassword());
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillEntity(resultSet);
                }
            }
        }catch (SQLException e){
            throw new DaoException("Can not find user by login and password", e);
        }
    }

    public User fillRolesToUser(User ob) throws DaoException{
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(
                    "SELECT id, role FROM role JOIN user_to_role ON role.id = user_to_role.id_role" +
                            " WHERE id_user = ?"
            )){
                prepared.setInt(1, ob.getId());
                try(ResultSet resultSet = prepared.executeQuery()){
                    ob.setRoles(fillListRoles(resultSet));
                    return ob;
                }
            }
        }catch (SQLException e){
            throw new DaoException("Can not find user by id", e);
        }
    }

    private List<Role> fillListRoles(ResultSet resultSet) throws DaoException{
        List<Role> roles = new ArrayList<>();
        try {
            while (resultSet.next()){
                Role role;
                role = new Role();
                role.setId(resultSet.getInt("id"));
                role.setRole(resultSet.getString("role"));
                roles.add(role);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill roles to user", e);
        }
        return roles;
    }

    @Override
    public List<User> fillListEntity(ResultSet resultSet) throws DaoException {
        List<User> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill list user entities", e);
        }

        return list;
    }

    public User fillEntity(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill user entity", e);
        }
        return user;
    }

}
