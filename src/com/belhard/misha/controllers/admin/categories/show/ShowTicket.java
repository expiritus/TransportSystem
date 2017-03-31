package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.impl.DaoRoute;
import com.belhard.misha.dao.impl.DaoTicket;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.Route;
import com.belhard.misha.entity.Ticket;
import com.belhard.misha.entity.User;
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
        DaoUser daoUser = new DaoUser();
        DaoRoute daoRoute = new DaoRoute();

        List<Ticket> tickets = daoTicket.findAll(Ticket.class);
        List<User> users = daoUser.findAll(User.class);
        List<Route> routes = daoRoute.findAll(Route.class);

        req.setAttribute("tickets", tickets);
        req.setAttribute("users", users);
        req.setAttribute("routes", routes);
        HttpUtils.forward(req, resp, "Tickets", "/WEB-INF/pages/admin/categories/show/ticket.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String deleteTicket = req.getParameter("deleteTicket");
        String addTicket = req.getParameter("addTicket");

        DaoTicket daoTicket = new DaoTicket();
        if(deleteTicket != null){
            int ticketId = Integer.parseInt(req.getParameter("deleteTicket"));
            daoTicket.delete(Ticket.class, ticketId);
        }else if(addTicket != null){
            int userId = Integer.parseInt(req.getParameter("userId"));
            int routeId = Integer.parseInt(req.getParameter("routeId"));
            String reservationStatusParam = req.getParameter("reservationStatus");
            boolean reservationStatus = false;
            if(reservationStatusParam != null && reservationStatusParam.equals("on")){
                reservationStatus = true;
            }
            String dateReservation = req.getParameter("dateReservation");
            String payStatusParam = req.getParameter("payStatus");
            boolean payStatus = false;
            if(payStatusParam != null && payStatusParam.equals("on")){
                payStatus = true;
            }

            String datePay = req.getParameter("datePat");
            Ticket ticket = new Ticket();
            ticket.setUserId(userId);
            ticket.setRouteId(routeId);
            ticket.setReservationStatus(reservationStatus);
            ticket.setDateReservation(dateReservation);
            ticket.setPayStatus(payStatus);
            ticket.setDatePay(datePay);
            daoTicket.insert(ticket);
        }

        HttpUtils.redirect(resp, req.getContextPath() + ShowTicket.URL);
    }
}
