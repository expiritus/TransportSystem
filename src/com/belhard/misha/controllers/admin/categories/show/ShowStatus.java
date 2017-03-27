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
        List<Status> statuses = daoStatus.findAll(Status.class);

        req.setAttribute("statuses", statuses);
        HttpUtils.forward(req, resp, "Statuses", "/WEB-INF/pages/admin/categories/show/status.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String addStatus = req.getParameter("addStatus");
        String deleteStatus = req.getParameter("deleteStatus");
        DaoStatus daoStatus = new DaoStatus();

        if(addStatus != null){
            String statusParam = req.getParameter("status");

            Status status = new Status();
            status.setStatus(statusParam);
            daoStatus.insert(status);
        }else if (deleteStatus != null){
            int statusId = Integer.parseInt(req.getParameter("deleteStatus"));
            daoStatus.delete(Status.class, statusId);
        }


        HttpUtils.redirect(resp, req.getContextPath() + ShowStatus.URL);

    }
}
