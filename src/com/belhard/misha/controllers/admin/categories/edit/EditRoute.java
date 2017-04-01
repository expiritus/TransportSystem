package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowRoute;
import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
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


@WebServlet("/admin/route/edit")
public class EditRoute extends HttpServlet {

    public static final String URL = "/admin/route/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String editRoute = req.getParameter("editRoute");
        String updateRoute = req.getParameter("updateRoute");

        DaoRoute daoRoute = (DaoRoute) DaoFactory.getDao(DaoTypes.Route);
        DaoTransport daoTransport = (DaoTransport) DaoFactory.getDao(DaoTypes.Transport);
        DaoCity daoCity = (DaoCity) DaoFactory.getDao(DaoTypes.City);
        DaoStatus daoStatus = (DaoStatus) DaoFactory.getDao(DaoTypes.Status);


        if (editRoute != null) {
            int editRouteId = Integer.parseInt(editRoute);
            List<Route> routes = daoRoute.findAll(Route.class);
            Route selectedRoute = daoRoute.findById(Route.class, editRouteId);
            List<Transport> transports = daoTransport.findAll(Transport.class);
            List<City> cities = daoCity.findAll(City.class);
            List<Status> statuses = daoStatus.findAll(Status.class);

            req.setAttribute("routes", routes);
            req.setAttribute("selectedRoute", selectedRoute);
            req.setAttribute("transports", transports);
            req.setAttribute("cities", cities);
            req.setAttribute("statuses", statuses);
            HttpUtils.forward(req, resp, "Edit route", "/WEB-INF/pages/admin/categories/edit/route.jsp");
            return;
        } else if (updateRoute != null) {
            int updateRouteId = Integer.parseInt(updateRoute);
            int transportId = Integer.parseInt(req.getParameter("transportId"));
            int statusId = Integer.parseInt(req.getParameter("statusId"));
            int cityFrom = Integer.parseInt(req.getParameter("cityFrom"));
            int cityTo = Integer.parseInt(req.getParameter("cityTo"));
            String timeDeparture = req.getParameter("timeDeparture");
            String arrivalTime = req.getParameter("arrivalTime");
            Route route = new Route();
            route.setId(updateRouteId);
            route.setTransportId(transportId);
            route.setStatusId(statusId);
            route.setFrom(cityFrom);
            route.setTo(cityTo);
            route.setTimeDeparture(timeDeparture);
            route.setArrivalTime(arrivalTime);
            daoRoute.update(route, updateRouteId);
        }

        HttpUtils.redirect(resp, ShowRoute.URL);
    }
}
