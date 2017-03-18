package com.belhard.misha.dao;

import com.belhard.misha.entity.UserToRole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoUserToRole extends DaoAbstract<UserToRole> {

    @Override
    public int insert(UserToRole ob) throws SQLException {
        return 0;
    }

    @Override
    public void update(UserToRole ob) throws SQLException {

    }

    @Override
    public List<UserToRole> fillListEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
