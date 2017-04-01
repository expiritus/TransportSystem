package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
import com.belhard.misha.dao.impl.DaoCity;
import com.belhard.misha.dao.impl.DaoCountry;
import com.belhard.misha.entity.City;
import com.belhard.misha.entity.Country;
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

        DaoCity daoCity = (DaoCity) DaoFactory.getDao(DaoTypes.City);
        DaoCountry daoCountry = (DaoCountry) DaoFactory.getDao(DaoTypes.Country);

        List<City> cities = daoCity.findAll(City.class);
        List<Country> countries = daoCountry.findAll(Country.class);

        req.setAttribute("cities", cities);
        req.setAttribute("countries", countries);


        HttpUtils.forward(req, resp, "Countries", "/WEB-INF/pages/admin/categories/show/city.jsp");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String addCity = req.getParameter("addCity");
        String deleteCity = req.getParameter("deleteCity");

        DaoCity daoCity = (DaoCity) DaoFactory.getDao(DaoTypes.City);
        if (addCity != null) {
            City city = new City();

            String cityParam = req.getParameter("city");
            city.setCity(cityParam);

            int countryId = Integer.parseInt(req.getParameter("country"));
            city.setCountryId(countryId);
            daoCity.insert(city);
        } else if (deleteCity != null) {
            int cityId = Integer.parseInt(req.getParameter("deleteCity"));
            try {
                daoCity.delete(City.class, cityId);
                HttpUtils.invalidateSessionByAttribute(req, "errorCityId");
                HttpUtils.invalidateSessionByAttribute(req, "errorDeleteCity");
            } catch (DaoException e) {
                HttpUtils.setSessionAttribute(req, "errorDeleteCity", "Городо не может быть удален потому что есть маршрут в этот город");
                HttpUtils.setSessionAttribute(req, "errorCityId", cityId);
            }
        }

        HttpUtils.redirect(resp, req.getContextPath() + ShowCity.URL);
    }
}
