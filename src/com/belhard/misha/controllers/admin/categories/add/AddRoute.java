package com.belhard.misha.controllers.admin.categories.add;

import com.belhard.misha.dao.impl.DaoRoute;
import com.belhard.misha.entity.Route;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/admin/route/add")
public class AddRoute extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int transport = Integer.parseInt(req.getParameter("transport"));
        int cityFrom = Integer.parseInt(req.getParameter("cityFrom"));
        int cityTo = Integer.parseInt(req.getParameter("cityTo"));
        String timeDeparture = req.getParameter("timeDeparture");
        String arrivalTime = req.getParameter("arrivalTime");

        DaoRoute daoRoute = new DaoRoute();
        Route route = new Route();
        route.setTransportId(transport);
        route.setFrom(cityFrom);
        route.setTo(cityTo);
        route.setStatusId(1);
        route.setTimeDeparture(timeDeparture);
        route.setArrivalTime(arrivalTime);
        daoRoute.insert(route);

        HttpUtils.referer(req, resp);

    }
}
