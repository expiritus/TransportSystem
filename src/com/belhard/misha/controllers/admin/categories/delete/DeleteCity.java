package com.belhard.misha.controllers.admin.categories.delete;

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


@WebServlet("/admin/city/delete")
public class DeleteCity extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int cityId = Integer.parseInt(req.getParameter("cityId"));

        DaoCity daoCity = new DaoCity();
        daoCity.delete(City.class, cityId);

        HttpUtils.referer(req, resp);
    }
}
