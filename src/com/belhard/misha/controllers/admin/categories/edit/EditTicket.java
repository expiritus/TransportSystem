package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowTicket;
import com.belhard.misha.dao.impl.*;
import com.belhard.misha.entity.*;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/ticket/edit")
public class EditTicket extends HttpServlet {

    public static final String URL = "/admin/ticket/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        String editTicket = req.getParameter("editTicket");
        String updateTicket = req.getParameter("updateTicket");

        DaoTicket daoTicket = new DaoTicket();
        DaoUser daoUser = new DaoUser();
        DaoRoute daoRoute = new DaoRoute();

        if(editTicket != null){
            int ticketId = Integer.parseInt(req.getParameter("editTicket"));
            Ticket selectedTicket = daoTicket.findById(Ticket.class, ticketId);
            List<User> users = daoUser.findAll(User.class);
            List<Route> routes = daoRoute.findAll(Route.class);
            req.setAttribute("selectedTicket", selectedTicket);
            req.setAttribute("users",users);
            req.setAttribute("routes", routes);
            HttpUtils.forward(req, resp, "Edit ticket", "/WEB-INF/pages/admin/categories/edit/ticket.jsp");
            return;
        }else if (updateTicket != null){
            int ticketId = Integer.parseInt(req.getParameter("updateTicket"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            int routeId = Integer.parseInt(req.getParameter("routeId"));
            boolean reservationStatus = Boolean.parseBoolean(req.getParameter("reservationStatus"));
            String dateReservation = req.getParameter("dateReservation");
            boolean payStatus = Boolean.parseBoolean(req.getParameter("payStatus"));
            String datePay = req.getParameter("datePay");
            Ticket ticket = new Ticket();
            ticket.setId(ticketId);
            ticket.setUserId(userId);
            ticket.setRouteId(routeId);
            ticket.setReservationStatus(reservationStatus);
            ticket.setDateReservation(dateReservation);
            ticket.setPayStatus(payStatus);
            if(datePay.equals("")){
                datePay = null;
            }
            ticket.setDatePay(datePay);
            daoTicket.update(ticket, ticketId);
        }

        HttpUtils.redirect(resp, ShowTicket.URL);
    }
}
