package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowCountry;
import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
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


@WebServlet("/admin/country/edit")
public class EditCountry extends HttpServlet {

    public static final String URL = "/admin/country/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editCountry = req.getParameter("editCountry");
        String updateCountry = req.getParameter("updateCountry");
        DaoCountry daoCountry = (DaoCountry) DaoFactory.getDao(DaoTypes.Country);
        if(editCountry != null){
            int editCountryId = Integer.parseInt(editCountry);
            Country country = daoCountry.findById(Country.class, editCountryId);
            req.setAttribute("country", country);
            HttpUtils.forward(req, resp, "Edit country", "/WEB-INF/pages/admin/categories/edit/country.jsp");
            return;
        }else if (updateCountry != null){
            int updateCountryId = Integer.parseInt(updateCountry);
            String countryParam = req.getParameter("country");
            Country country = new Country();
            country.setId(updateCountryId);
            country.setCountry(countryParam);
            daoCountry.update(country, updateCountryId);
        }

        HttpUtils.redirect(resp, ShowCountry.URL);
    }
}
