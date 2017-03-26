package com.belhard.misha.dao.impl;

import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoStatus extends DaoAbstract<Status> {


    @Override
    public List<Status> fillListEntity(ResultSet resultSet) throws DaoException {
        List<Status> statuses = new ArrayList<>();
        try {
            while (resultSet.next()){
                Status status = new Status();
                status.setId(resultSet.getInt("id"));
                status.setStatus(resultSet.getString("status"));
                statuses.add(status);
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill list status entities", e);
        }
        return statuses;
    }


    @Override
    public Status fillEntity(ResultSet resultSet) throws DaoException {
        Status status = new Status();
        try {
            if(resultSet.next()){
                status.setId(resultSet.getInt("id"));
                status.setStatus(resultSet.getString("status"));
            }
        }catch (SQLException e){
            throw new DaoException("Can not fill status entity", e);
        }
        return status;
    }
}
