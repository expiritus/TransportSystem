package com.belhard.misha.dao.impl;

import com.belhard.misha.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoStatus extends DaoAbstract<Status> {


    @Override
    public List<Status> fillListEntity(ResultSet resultSet) throws SQLException {
        List<Status> statuses = new ArrayList<>();
        while (resultSet.next()){
            Status status = new Status();
            status.setId(resultSet.getInt("id"));
            status.setStatus(resultSet.getString("status"));
            statuses.add(status);
        }
        return statuses;
    }


    @Override
    public Status fillEntity(ResultSet resultSet) throws SQLException {
        Status status = new Status();
        if(resultSet.next()){
            status.setId(resultSet.getInt("id"));
            status.setStatus(resultSet.getString("status"));
        }
        return status;
    }
}
