package com.belhard.misha.controllers.personalArea;

import com.belhard.misha.controllers.admin.auth.LoginController;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class IndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(HttpUtils.getSessionAttribute(req, "authUser") == null){
            HttpUtils.redirect(resp, LoginController.URL);
            return;
        }

        HttpUtils.forward(req, resp, "Personal Area", "/WEB-INF/pages/personal-area/index.jsp");

    }
}
