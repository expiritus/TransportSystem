package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.impl.DaoCity;
import com.belhard.misha.dao.impl.DaoRoute;
import com.belhard.misha.dao.impl.DaoStatus;
import com.belhard.misha.dao.impl.DaoTransport;
import com.belhard.misha.entity.City;
import com.belhard.misha.entity.Route;
import com.belhard.misha.entity.Transport;
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


@WebServlet("/admin/route")
public class ShowRoute extends HttpServlet {

    public static final String URL = "/admin/route";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        DaoRoute daoRoute = new DaoRoute();
        DaoTransport daoTransport = new DaoTransport();
        DaoCity daoCity = new DaoCity();
        List<Route> routes = null;
        List<Transport> transports = null;
        List<City> cities = null;
        try {
            routes = daoRoute.findAll(Route.class);
            transports = daoTransport.findAll(Transport.class);
            cities = daoCity.findAll(City.class);
        } catch (SQLException e) {
            throw new RuntimeException("Can not find routes", e);
        }

        req.setAttribute("routes", routes);
        req.setAttribute("transports", transports);
        req.setAttribute("cities", cities);
        HttpUtils.forward(req, resp, "Routes", "/WEB-INF/pages/admin/categories/show/route.jsp");
    }
}
