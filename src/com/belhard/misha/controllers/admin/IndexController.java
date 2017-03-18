package com.belhard.misha.controllers.admin;


import com.belhard.misha.utils.HttpUtils;
import com.belhard.misha.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(SessionUtils.getSessionAttribute(req, "auth") == null){
            HttpUtils.redirect(resp, "/login");
            return;
        }

        HttpUtils.forward(req, resp, "Admin Panel", "/WEB-INF/pages/admin/index.jsp");
    }
}