package com.belhard.misha.controllers.admin.categories.delete;

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


@WebServlet("/admin/status/delete")
public class DeleteStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int stausId = Integer.parseInt(req.getParameter("statusId"));
        DaoStatus daoStatus = new DaoStatus();
        daoStatus.delete(Status.class, stausId);

        HttpUtils.referer(req, resp);
    }
}
