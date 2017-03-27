package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.impl.DaoTicket;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.Ticket;
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


@WebServlet("/admin/ticket")
public class ShowTicket extends HttpServlet {

    public static final String URL = "/admin/ticket";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        DaoTicket daoTicket = new DaoTicket();

        List<Ticket> tickets = daoTicket.findAll(Ticket.class);

        req.setAttribute("tickets", tickets);
        HttpUtils.forward(req, resp, "Tickets", "/WEB-INF/pages/admin/categories/show/ticket.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String deleteTicket = req.getParameter("deleteTicket");
        if(deleteTicket != null){
            int ticketId = Integer.parseInt(req.getParameter("deleteTicket"));
            DaoTicket daoTicket = new DaoTicket();
            daoTicket.delete(Ticket.class, ticketId);
        }

        HttpUtils.redirect(resp, req.getContextPath() + ShowTicket.URL);
    }
}
