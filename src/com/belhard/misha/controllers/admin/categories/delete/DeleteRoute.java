package com.belhard.misha.controllers.admin.categories.delete;

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


@WebServlet("/admin/route/delete")
public class DeleteRoute extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int routeId = Integer.parseInt(req.getParameter("routeId"));

        DaoRoute daoRoute = new DaoRoute();
        daoRoute.delete(Route.class, routeId);

        HttpUtils.referer(req, resp);
    }
}
