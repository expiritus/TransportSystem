package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowStatus;
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


@WebServlet("/admin/status/edit")
public class EditStatus extends HttpServlet {

    public static final String URL = "/admin/status/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }


        String editStatus = req.getParameter("editStatus");
        String updateStatus = req.getParameter("updateStatus");
        DaoStatus daoStatus = new DaoStatus();

        if(editStatus != null){
            int statusId = Integer.parseInt(editStatus);
            Status status = daoStatus.findById(Status.class, statusId);
            req.setAttribute("status", status);
            HttpUtils.forward(req, resp, "Edit status", "/WEB-INF/pages/admin/categories/edit/status.jsp");
            return;
        }else if (updateStatus != null){
            int statusId = Integer.parseInt(updateStatus);
            String statusParam = req.getParameter("status");
            Status status = new Status();
            status.setId(statusId);
            status.setStatus(statusParam);
            daoStatus.update(status, statusId);
        }


        HttpUtils.redirect(resp, ShowStatus.URL);


    }
}
