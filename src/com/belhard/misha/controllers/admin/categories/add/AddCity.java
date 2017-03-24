package com.belhard.misha.controllers.admin.categories.add;

import com.belhard.misha.dao.impl.DaoCity;
import com.belhard.misha.entity.City;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/admin/city/add")
public class AddCity extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String cityParam = req.getParameter("city");
        int countryId = Integer.parseInt(req.getParameter("country"));

        DaoCity daoCity = new DaoCity();
        City city = new City();
        city.setCity(cityParam);
        city.setCountryId(countryId);
        try {
            daoCity.insert(city);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Can not insert city", e);
        }

        HttpUtils.referer(req, resp);

    }
}
