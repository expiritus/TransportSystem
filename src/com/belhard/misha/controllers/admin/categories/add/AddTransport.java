package com.belhard.misha.controllers.admin.categories.add;

import com.belhard.misha.dao.impl.DaoTransport;
import com.belhard.misha.entity.Transport;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/admin/transport/add")
public class AddTransport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String model = req.getParameter("model");
        int capacity = Integer.parseInt(req.getParameter("capacity"));
        double speed = Double.parseDouble(req.getParameter("speed"));
        int transportTypeId = Integer.parseInt(req.getParameter("transportType"));

        DaoTransport daoTransport = new DaoTransport();
        Transport transport = new Transport();
        transport.setTransportTypeId(transportTypeId);
        transport.setModel(model);
        transport.setCapacity(capacity);
        transport.setSpeed(speed);
        daoTransport.insert(transport);

        HttpUtils.referer(req, resp);
    }
}
