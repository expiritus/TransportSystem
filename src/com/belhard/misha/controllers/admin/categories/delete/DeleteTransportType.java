package com.belhard.misha.controllers.admin.categories.delete;

import com.belhard.misha.dao.impl.DaoTransportType;
import com.belhard.misha.entity.TransportType;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/transport-type/delete")
public class DeleteTransportType extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int transportTypeId = Integer.parseInt(req.getParameter("transportTypeId"));

        DaoTransportType daoTransportType = new DaoTransportType();
        daoTransportType.delete(TransportType.class, transportTypeId);

        HttpUtils.referer(req, resp);
    }
}
