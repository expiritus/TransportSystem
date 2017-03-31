package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowTransport;
import com.belhard.misha.controllers.admin.categories.show.ShowTransportType;
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


@WebServlet("/admin/transport-type/edit")
public class EditTransportType extends HttpServlet {

    public static final String URL = "/admin/transport-type/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        String editTransportType = req.getParameter("editTransportType");
        String updateTransportType = req.getParameter("updateTransportType");
        DaoTransportType daoTransportType = new DaoTransportType();

        if(editTransportType != null){
            int transportTypeId = Integer.parseInt(req.getParameter("editTransportType"));
            TransportType transportType = daoTransportType.findById(TransportType.class, transportTypeId);

            req.setAttribute("transportType", transportType);
            HttpUtils.forward(req, resp, "Edit transport type", "/WEB-INF/pages/admin/categories/edit/transport-type.jsp");
            return;
        }else if (updateTransportType != null){
            int transportTypeId = Integer.parseInt(updateTransportType);
            String type = req.getParameter("type");
            TransportType transportType = new TransportType();
            transportType.setId(transportTypeId);
            transportType.setType(type);
            daoTransportType.update(transportType, transportTypeId);
        }

        HttpUtils.redirect(resp, ShowTransportType.URL);
    }
}
