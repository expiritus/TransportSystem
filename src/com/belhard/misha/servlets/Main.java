package com.belhard.misha.servlets;


import com.belhard.misha.dao.DaoCity;
import com.belhard.misha.entity.City;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/index.html")
public class Main extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoCity daoCity = new DaoCity();
        try {
            daoCity.delete(new City());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
