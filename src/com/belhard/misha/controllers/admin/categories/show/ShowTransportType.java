package com.belhard.misha.controllers.admin.categories.show;


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
import java.util.List;

@WebServlet("/admin/transport-type")
public class ShowTransportType extends HttpServlet {

    public static final String URL = "/admin/transport-type";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        DaoTransportType daoTransportType = new DaoTransportType();

        List<TransportType> transportTypes = daoTransportType.findAll(TransportType.class);

        req.setAttribute("transportTypes", transportTypes);
        HttpUtils.forward(req, resp, "Transport type", "/WEB-INF/pages/admin/categories/show/transport-type.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String addTransportType = req.getParameter("addTransportType");
        String deleteTransportType = req.getParameter("deleteTransportType");

        DaoTransportType daoTransportType = new DaoTransportType();
        if(addTransportType != null){

            String transportTypeParam = req.getParameter("transportType");
            TransportType transportType = new TransportType();
            transportType.setType(transportTypeParam);
            daoTransportType.insert(transportType);
        }else if (deleteTransportType != null){
            int transportTypeId = Integer.parseInt(req.getParameter("deleteTransportType"));
            daoTransportType.delete(TransportType.class, transportTypeId);
        }

        HttpUtils.redirect(resp, req.getContextPath() + ShowTransportType.URL);

    }
}
