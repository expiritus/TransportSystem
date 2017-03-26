package com.belhard.misha.controllers.admin.categories.add;

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

@WebServlet("/admin/status/add")
public class AddStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String statusParam = req.getParameter("status");

        DaoStatus daoStatus = new DaoStatus();
        Status status = new Status();
        status.setStatus(statusParam);
        daoStatus.insert(status);

        HttpUtils.referer(req, resp);

    }
}
