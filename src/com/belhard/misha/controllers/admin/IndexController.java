package com.belhard.misha.controllers.admin;


import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class IndexController extends HttpServlet {

    public static final String URL = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        User authUser = (User) HttpUtils.getSessionAttribute(req, "authUser");
        for (Role role : authUser.getRoles()) {
            if (role.getRole().equals("admin") || role.getRole().equals("manager")) {
                HttpUtils.forward(req, resp, "Admin Panel", "/WEB-INF/pages/admin/index.jsp");
                return;
            }
        }

        HttpUtils.redirect(resp, "index.html");
    }
}
