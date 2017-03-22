package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.impl.DaoTransport;
import com.belhard.misha.dao.impl.DaoTransportType;
import com.belhard.misha.entity.Transport;
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
import java.util.List;


@WebServlet("/admin/transport")
public class TransportCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        DaoTransport daoTransport = new DaoTransport();
        List<Transport> transports = null;
        try {
            transports = daoTransport.findAll(Transport.class);
        } catch (SQLException e) {
            throw new RuntimeException("Can not find transports", e);
        }

        req.setAttribute("transports", transports);
        HttpUtils.forward(req, resp, "TransportCategory", "/WEB-INF/pages/admin/categories/show/transport.jsp");
    }
}
