package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.impl.DaoCity;
import com.belhard.misha.dao.impl.DaoRoute;
import com.belhard.misha.dao.impl.DaoStatus;
import com.belhard.misha.dao.impl.DaoTransport;
import com.belhard.misha.entity.City;
import com.belhard.misha.entity.Route;
import com.belhard.misha.entity.Status;
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
        DaoStatus daoStatus = new DaoStatus();

        List<Route> routes = daoRoute.findAll(Route.class);
        List<Transport> transports = daoTransport.findAll(Transport.class);
        List<City> cities = daoCity.findAll(City.class);
        List<Status> statuses = daoStatus.findAll(Status.class);

        req.setAttribute("routes", routes);
        req.setAttribute("transports", transports);
        req.setAttribute("cities", cities);
        req.setAttribute("statuses", statuses);
        HttpUtils.forward(req, resp, "Routes", "/WEB-INF/pages/admin/categories/show/route.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String addRoute = req.getParameter("addRoute");
        String deleteRoute = req.getParameter("deleteRoute");

        DaoRoute daoRoute = new DaoRoute();
        if(addRoute != null){
            int transport = Integer.parseInt(req.getParameter("transport"));
            int cityFrom = Integer.parseInt(req.getParameter("cityFrom"));
            int cityTo = Integer.parseInt(req.getParameter("cityTo"));
            int statusId = Integer.parseInt(req.getParameter("statusId"));
            String timeDeparture = req.getParameter("timeDeparture");
            String arrivalTime = req.getParameter("arrivalTime");

            Route route = new Route();
            route.setTransportId(transport);
            route.setFrom(cityFrom);
            route.setTo(cityTo);
            route.setStatusId(statusId);
            route.setTimeDeparture(timeDeparture);
            route.setArrivalTime(arrivalTime);
            daoRoute.insert(route);
        }else if(deleteRoute != null){
            int routeId = Integer.parseInt(req.getParameter("deleteRoute"));
            daoRoute.delete(Route.class, routeId);
        }


        HttpUtils.redirect(resp, req.getContextPath() + ShowRoute.URL);

    }
}
