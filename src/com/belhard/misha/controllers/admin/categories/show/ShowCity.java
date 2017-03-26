package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.impl.DaoCity;
import com.belhard.misha.dao.impl.DaoCountry;
import com.belhard.misha.entity.City;
import com.belhard.misha.entity.Country;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/city")
public class ShowCity extends HttpServlet {

    public static final String URL = "/admin/city";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        DaoCity daoCity = new DaoCity();
        DaoCountry daoCountry = new DaoCountry();

        List<City> cities = daoCity.findAll(City.class);
        List<Country> countries = daoCountry.findAll(Country.class);

        req.setAttribute("cities", cities);
        req.setAttribute("countries", countries);


        HttpUtils.forward(req, resp, "Countries", "/WEB-INF/pages/admin/categories/show/city.jsp");
    }
}