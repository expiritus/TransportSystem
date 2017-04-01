package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowTransport;
import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
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


@WebServlet("/admin/transport/edit")
public class EditTransport extends HttpServlet {

    public static final String URL = "/admin/transport/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String editTransport = req.getParameter("editTransport");
        String updateTransport = req.getParameter("updateTransport");
        DaoTransport daoTransport = (DaoTransport) DaoFactory.getDao(DaoTypes.Transport);
        DaoTransportType daoTransportType = (DaoTransportType) DaoFactory.getDao(DaoTypes.TransportType);
        if (editTransport != null) {
            int transportId = Integer.parseInt(req.getParameter("editTransport"));
            Transport selectedTransport = daoTransport.findById(Transport.class, transportId);
            List<TransportType> transportTypes = daoTransportType.findAll(TransportType.class);

            req.setAttribute("selectedTransport", selectedTransport);
            req.setAttribute("transportTypes", transportTypes);
            HttpUtils.forward(req, resp, "Edit transport", "/WEB-INF/pages/admin/categories/edit/transport.jsp");
            return;
        } else if (updateTransport != null) {
            int transportId = Integer.parseInt(updateTransport);
            int transportTypeId = Integer.parseInt(req.getParameter("transportTypeId"));
            String model = req.getParameter("model");
            int capacity = Integer.parseInt(req.getParameter("capacity"));
            float speed = Float.parseFloat(req.getParameter("speed"));
            Transport transport = new Transport();
            transport.setId(transportId);
            transport.setTransportTypeId(transportTypeId);
            transport.setModel(model);
            transport.setCapacity(capacity);
            transport.setSpeed(speed);
            daoTransport.update(transport, transportId);
        }

        HttpUtils.redirect(resp, ShowTransport.URL);
    }
}
