package com.belhard.misha.controllers.admin;

import com.belhard.misha.dao.DaoUser;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.HttpUtils;
import com.belhard.misha.utils.SessionUtils;
import com.belhard.misha.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        HttpUtils.forward(req, resp, "Login", "/WEB-INF/pages/admin/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        String login = req.getParameter("login");
        String password = StringUtils.MD5(req.getParameter("password"));
        DaoUser daoUser = new DaoUser();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        try {
            user = daoUser.findByLogin(user);

            if(user.getId() == 0){
                HttpUtils.referer(req, resp);
                return;
            }

            SessionUtils.setSessionAttribute(req, "auth", user);
            HttpUtils.redirect(resp, "/admin");

        } catch (SQLException e) {
            SessionUtils.removeSession(req, "auth");
        }

    }
}
