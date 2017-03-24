package com.belhard.misha.controllers.admin.categories.show;


import com.belhard.misha.dao.impl.DaoStatus;
import com.belhard.misha.entity.Status;
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

@WebServlet("/admin/status")
public class ShowStatus extends HttpServlet {

    public static final String URL = "/admin/status";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        DaoStatus daoStatus = new DaoStatus();
        List<Status> statuses = null;
        try {
            statuses = daoStatus.findAll(Status.class);
        } catch (SQLException e) {
            throw new RuntimeException("Can not find statuses");
        }

        req.setAttribute("statuses", statuses);
        HttpUtils.forward(req, resp, "Statuses", "/WEB-INF/pages/admin/categories/show/status.jsp");
    }
}
