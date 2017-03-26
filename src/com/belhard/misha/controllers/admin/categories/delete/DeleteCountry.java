package com.belhard.misha.controllers.admin.categories.delete;

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


@WebServlet("/admin/country/delete")
public class DeleteCountry extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int countryId = Integer.parseInt(req.getParameter("countryId"));

        DaoCountry daoCountry = new DaoCountry();
        daoCountry.delete(Country.class, countryId);

        HttpUtils.referer(req, resp);
    }
}
