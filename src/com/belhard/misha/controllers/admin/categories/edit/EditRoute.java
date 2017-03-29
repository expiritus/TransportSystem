package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowRole;
import com.belhard.misha.dao.impl.DaoCity;
import com.belhard.misha.dao.impl.DaoRoute;
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
import java.util.List;


@WebServlet("/admin/route/edit")
public class EditRoute extends HttpServlet {

    public static final String URL = "/admin/route/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        String editRoute = req.getParameter("editRoute");
        String updateRoute = req.getParameter("updateRoute");

        DaoRoute daoRoute = new DaoRoute();
        DaoTransport daoTransport = new DaoTransport();
        DaoCity daoCity = new DaoCity();


        if (editRoute != null) {
            int editRouteId = Integer.parseInt(editRoute);
            List<Route> routes = daoRoute.findAll(Route.class);
            Route selectedRoute = daoRoute.findById(Route.class, editRouteId);
            List<Transport> transports = daoTransport.findAll(Transport.class);
            List<City> cities = daoCity.findAll(City.class);

            req.setAttribute("routes", routes);
            req.setAttribute("selectedRoute", selectedRoute);
            req.setAttribute("transports", transports);
            req.setAttribute("cities", cities);
            HttpUtils.forward(req, resp, "Edit route", "/WEB-INF/pages/admin/categories/edit/route.jsp");
            return;
        } else if (updateRoute != null) {
            int updateRouteId = Integer.parseInt(updateRoute);
        }

        HttpUtils.redirect(resp, ShowRole.URL);
    }
}
