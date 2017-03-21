package com.belhard.misha.controllers.personalArea;

import com.belhard.misha.controllers.admin.auth.LoginController;
import com.belhard.misha.entity.User;
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

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        User user = (User) HttpUtils.getSessionAttribute(req, "authUser");
        if(AuthUtils.isAdmin(user) || AuthUtils.isManager(user)){
            req.setAttribute("adminPanel", true);
        }

        HttpUtils.forward(req, resp, "Personal Area", "/WEB-INF/pages/personal-area/index.jsp");

    }
}
