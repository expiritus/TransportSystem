package com.belhard.misha.controllers.admin.categories.delete;

import com.belhard.misha.dao.impl.DaoTicket;
import com.belhard.misha.entity.Ticket;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/ticket/delete")
public class DeleteTicket extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int ticketId = Integer.parseInt(req.getParameter("ticketId"));
        DaoTicket daoTicket = new DaoTicket();
        daoTicket.delete(Ticket.class, ticketId);

        HttpUtils.referer(req, resp);
    }
}
