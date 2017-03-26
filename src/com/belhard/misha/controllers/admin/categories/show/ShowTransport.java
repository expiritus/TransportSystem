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
import java.util.List;


@WebServlet("/admin/transport")
public class ShowTransport extends HttpServlet {

    public static final String URL = "/admin/transport";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        DaoTransport daoTransport = new DaoTransport();

        DaoTransportType daoTransportType = new DaoTransportType();

        List<Transport> transports = daoTransport.findAll(Transport.class);
        List<TransportType> transportTypes = daoTransportType.findAll(TransportType.class);

        req.setAttribute("transports", transports);
        req.setAttribute("transportTypes", transportTypes);
        HttpUtils.forward(req, resp, "ShowTransport", "/WEB-INF/pages/admin/categories/show/transport.jsp");
    }
}