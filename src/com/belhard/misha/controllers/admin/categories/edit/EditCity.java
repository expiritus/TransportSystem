package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowCity;
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

@WebServlet("/admin/city/edit")
public class EditCity extends HttpServlet {

    public static final String URL = "/admin/city/edit";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        String editCity = req.getParameter("editCity");
        if(editCity != null){
            int editCityId = Integer.parseInt(req.getParameter("editCity"));
            String countryParam = req.getParameter("country");

            DaoCity daoCity = new DaoCity();
            City city = daoCity.findById(City.class, editCityId);

            DaoCountry daoCountry = new DaoCountry();
            List<Country> countries = daoCountry.findAll(Country.class);

            req.setAttribute("city", city);
            req.setAttribute("countries", countries);
            req.setAttribute("selected", countryParam);
            HttpUtils.forward(req, resp, "Edit city", "/WEB-INF/pages/admin/categories/edit/city.jsp");
            return;
        }

        HttpUtils.redirect(resp, ShowCity.URL);

    }
}
