package com.belhard.misha.controllers.main;

import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index.html")
public class IndexController extends HttpServlet {

    public static final String URL = "index.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);



        HttpUtils.forward(req, resp, "Home", "/WEB-INF/pages/main/index.jsp");
    }
}