package com.belhard.misha.controllers.admin.categories.show;

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
import java.util.List;


@WebServlet("/admin/route")
public class RouteCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        DaoRoute daoRoute = new DaoRoute();
        List<Route> routes = null;
        try {
            routes = daoRoute.findAll(Route.class);
        } catch (SQLException e) {
            throw new RuntimeException("Can not find routes", e);
        }

        req.setAttribute("routes", routes);
        HttpUtils.forward(req, resp, "Routes", "/WEB-INF/pages/admin/categories/show/route.jsp");
    }
}
