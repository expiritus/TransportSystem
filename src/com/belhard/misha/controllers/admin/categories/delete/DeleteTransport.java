package com.belhard.misha.controllers.admin.categories.delete;

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


@WebServlet("/admin/transport/delete")
public class DeleteTransport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int transportId = Integer.parseInt(req.getParameter("transportId"));

        DaoTransport daoTransport = new DaoTransport();
        daoTransport.delete(Transport.class, transportId);


        HttpUtils.referer(req, resp);
    }
}
