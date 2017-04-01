package com.belhard.misha.controllers.admin.categories.show;


import com.belhard.misha.dao.exceptions.DaoException;
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
import java.util.List;


@WebServlet("/admin/country")
public class ShowCountry extends HttpServlet {

    public static final String URL = "/admin/country";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoCountry daoCountry = (DaoCountry) DaoFactory.getDao(DaoTypes.Country);
        List<Country> countries = daoCountry.findAll(Country.class);

        req.setAttribute("countries", countries);
        HttpUtils.forward(req, resp, "Countries", "/WEB-INF/pages/admin/categories/show/country.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String countryParam = req.getParameter("country");
        String addCountry = req.getParameter("addCountry");
        String deleteCountry = req.getParameter("deleteCountry");

        DaoCountry daoCountry = (DaoCountry) DaoFactory.getDao(DaoTypes.Country);
        Country country = new Country();
        country.setCountry(countryParam);
        if(addCountry != null){
            daoCountry.insert(country);
        }else if (deleteCountry != null){
            int countryId = Integer.parseInt(deleteCountry);
            try{
                daoCountry.delete(Country.class, countryId);
                HttpUtils.invalidateSessionByAttribute(req, "errorCountryId");
                HttpUtils.invalidateSessionByAttribute(req, "errorDeleteCity");
            }catch (DaoException e){
                HttpUtils.setSessionAttribute(req, "errorCountryId", countryId);
                HttpUtils.setSessionAttribute(req, "errorDeleteCountry", "Страна не может быть удалена пока у нее есть города");
            }
        }

        HttpUtils.redirect(resp, req.getContextPath() + ShowCountry.URL);
    }

}
