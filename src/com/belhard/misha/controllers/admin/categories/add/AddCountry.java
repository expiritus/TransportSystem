package com.belhard.misha.controllers.admin.categories.add;

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

@WebServlet("/admin/country/add")
public class AddCountry extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String countryParam = req.getParameter("country");

        DaoCountry daoCountry = new DaoCountry();
        Country country = new Country();
        country.setCountry(countryParam);
        try {
            daoCountry.insert(country);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Can not insert country", e);
        }

        HttpUtils.referer(req, resp);
    }
}
