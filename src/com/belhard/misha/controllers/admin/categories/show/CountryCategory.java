package com.belhard.misha.controllers.admin.categories.show;


import com.belhard.misha.dao.impl.DaoCountry;
import com.belhard.misha.entity.Country;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/admin/country")
public class CountryCategory extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        DaoCountry daoCountry = new DaoCountry();
        List<Country> countries = null;
        try {
            countries = daoCountry.findAll(Country.class);
        } catch (SQLException e) {
            throw new RuntimeException("Can not find countries", e);
        }

        req.setAttribute("countries", countries);
        HttpUtils.forward(req, resp, "Countries", "/WEB-INF/pages/admin/categories/show/country.jsp");
    }

}
