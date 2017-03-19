package com.belhard.misha.controllers.admin.auth;

import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;
import com.belhard.misha.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    public static final String URL = "login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        HttpUtils.forward(req, resp, "Login", "/WEB-INF/pages/admin/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int countValidateFields = validateFields(req, login, password);
        if (countValidateFields < 2) {
            stateFull(req, login);
            HttpUtils.redirect(resp, LoginController.URL);
            return;
        }
        password = StringUtils.MD5(password);
        DaoUser daoUser = new DaoUser();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        try {
            user = daoUser.findByLogin(user);
            AuthUtils.authUser(req, resp, user, login);
        } catch (SQLException e) {
            HttpUtils.removeSession(req, "authUser");
        }
    }

    private int validateFields(HttpServletRequest req, String login, String password) {
        String requiredText = "Поле не может быть пустым";
        int countValidateFields = 0;
        if (StringUtils.isEmpty(login) || StringUtils.isBlank(login)) {
            HttpUtils.setSessionAttribute(req, "errorValidateLogin", requiredText);
            HttpUtils.invalidateByAttribute(req, "userNotFound");
        } else {
            HttpUtils.invalidateByAttribute(req, "errorValidateLogin");
            countValidateFields++;
        }

        if (StringUtils.isEmpty(password) || StringUtils.isBlank(password)) {
            HttpUtils.setSessionAttribute(req, "errorValidatePassword", requiredText);
            HttpUtils.invalidateByAttribute(req, "userNotFound");
        } else {
            HttpUtils.invalidateByAttribute(req, "errorValidatePassword");
            countValidateFields++;
        }
        return countValidateFields;
    }

    private void stateFull(HttpServletRequest req, String login){
        HttpUtils.setSessionAttribute(req, "login", login);
    }
}
