package com.belhard.misha.dao;

import com.belhard.misha.db.ConnectDb;
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
        return 0;
    }

    @Override
    public void update(User ob) throws SQLException {

    }

    public User findByLogin(User ob) throws SQLException{
        try (Connection connection = ConnectDb.getInstance().getConnection()){
            try(PreparedStatement prepared = connection.prepareStatement(
                    "SELECT * FROM " + ob.getClass().getSimpleName().toLowerCase() +
                    " WHERE login = ? AND password = ?")
            ) {
                prepared.setString(1, ob.getLogin());
                prepared.setString(2, ob.getPassword());
                try(ResultSet resultSet = prepared.executeQuery()){
                    return fillEntity(resultSet);
                }
            }
        }
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
