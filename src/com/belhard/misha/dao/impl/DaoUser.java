package com.belhard.misha.dao.impl;

import com.belhard.misha.db.ConnectDb;
import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUser extends DaoAbstract<User> {

    @Override
    public int insert(User ob) throws SQLException {
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(
                    "INSERT INTO " + ob.getClass().getSimpleName().toLowerCase() +
                            " (name, login, email, password) " +
                            " VALUES (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS
            )){
                prepared.setString(1, ob.getName());
                prepared.setString(2, ob.getLogin());
                prepared.setString(3, ob.getEmail());
                prepared.setString(4, ob.getPassword());
                prepared.execute();
                return lastInsertedId(prepared);
            }
        }
    }

    public void assignRoleUser(User ob) throws SQLException{
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(
                    "INSERT INTO user_to_role (id_user, id_role) " +
                            "VALUES (?, (SELECT id FROM role WHERE role='user'))"
            )){
                prepared.setInt(1, ob.getId());
                prepared.execute();
            }
        }
    }


    @Override
    public void update(User ob) throws SQLException {

    }

    public User findByLoginAndPassword(User ob) throws SQLException{
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
        }
    }

    public User getRolesByUserId(User ob) throws SQLException{
        try(Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(
                    "SELECT role FROM role JOIN user_to_role ON role.id = user_to_role.id_role" +
                            " WHERE id_user = ?"
            )){
                prepared.setInt(1, ob.getId());
                try(ResultSet resultSet = prepared.executeQuery()){
                    ob.setRoles(fillRolesToUser(resultSet));
                    return ob;
                }
            }
        }
    }

    private List<Role> fillRolesToUser(ResultSet resultSet) throws SQLException{
        List<Role> roles = new ArrayList<>();
        while (resultSet.next()){
            Role role = new Role();
            role.setRole(resultSet.getString("role"));
            roles.add(role);
        }
        return roles;
    }

    public User fillEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setLogin(resultSet.getString("login"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }
        return user;
    }

    @Override
    public List<User> fillListEntity(ResultSet resultSet) throws SQLException {
        List<User> list = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setLogin(resultSet.getString("login"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            list.add(user);
        }
        return list;
    }
}
