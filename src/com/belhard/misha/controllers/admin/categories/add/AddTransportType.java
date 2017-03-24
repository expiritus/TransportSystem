package com.belhard.misha.controllers.admin.categories.add;

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
import java.sql.SQLException;


@WebServlet("/admin/transport-type/add")
public class AddTransportType extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String transportTypeParam = req.getParameter("transportType");

        DaoTransportType daoTransportType = new DaoTransportType();
        TransportType transportType = new TransportType();
        transportType.setType(transportTypeParam);
        try {
            daoTransportType.insert(transportType);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException("Can not insert transport type", e);
        }

        HttpUtils.referer(req, resp);

    }
}
