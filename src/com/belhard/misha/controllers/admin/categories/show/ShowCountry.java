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
import java.util.List;


@WebServlet("/admin/country")
public class ShowCountry extends HttpServlet {

    public static final String URL = "/admin/country";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        DaoCountry daoCountry = new DaoCountry();
        List<Country> countries = null;
        countries = daoCountry.findAll(Country.class);

        req.setAttribute("countries", countries);
        HttpUtils.forward(req, resp, "Countries", "/WEB-INF/pages/admin/categories/show/country.jsp");
    }

}