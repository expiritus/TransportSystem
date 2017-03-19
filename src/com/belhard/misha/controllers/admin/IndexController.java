package com.belhard.misha.controllers.admin;


import com.belhard.misha.controllers.admin.auth.LoginController;
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

        if (HttpUtils.getSessionAttribute(req, "authUser") == null) {
            HttpUtils.redirect(resp, LoginController.URL);
            return;
        }

        HttpUtils.forward(req, resp, "Admin Panel", "/WEB-INF/pages/admin/index.jsp");
    }
}
