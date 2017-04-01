package com.belhard.misha.controllers.main;

import com.belhard.misha.entity.User;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index.html")
public class IndexController extends HttpServlet {

    public static final String MAIN_URL = "/index.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if(HttpUtils.getSessionAttribute(req, "authUser") != null){
            User authUser = (User) HttpUtils.getSessionAttribute(req, "authUser");

        }


        HttpUtils.forward(req, resp, "Home", "/WEB-INF/pages/main/index.jsp");
    }
}