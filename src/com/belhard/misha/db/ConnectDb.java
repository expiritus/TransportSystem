package com.belhard.misha.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDb {
    private static ConnectDb ourInstance = new ConnectDb();
    private DataSource dataSource;

    public static ConnectDb getInstance() {
        return ourInstance;
    }

    private ConnectDb() {
        try {
            Context initContext = new InitialContext();
            Context rootContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) rootContext.lookup("jdbc/transport_db");
        }catch (NamingException e){
            throw new RuntimeException("Some errors occurred during DataSource lookup!", e);
        }
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException("Can not receive connection!", e);
        }
    }

    public void closeDbResources(Connection connection, Statement statement, ResultSet resultSet){
        closeResultSet(resultSet);
        closeStatement(statement);
        closeConnection(connection);
    }

    private void closeResultSet(ResultSet resultSet){
        if (resultSet != null){
             try {
                 resultSet.close();
             }catch (SQLException e){
                 System.out.println("Error: ResultSet has not been closed!");
             }
        }
    }

    private void closeStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            }catch (SQLException e){
                System.out.println("Error: Statement has not been closed!");
            }
        }
    }

    private void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            }catch (SQLException e){
                System.out.println("Error: Connection has not been closed!");
            }
        }
    }
}
