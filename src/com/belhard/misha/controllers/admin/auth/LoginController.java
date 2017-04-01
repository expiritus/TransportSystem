package com.belhard.misha.controllers.admin.auth;

import com.belhard.misha.dao.exceptions.DaoException;
import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;
import com.belhard.misha.utils.PropertyUtils;
import com.belhard.misha.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    public static final String URL = "/login";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpUtils.forward(req, resp, "Login", "/WEB-INF/pages/admin/auth/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Map<String, String> errorMap = validateFields(login, password);
        if (errorMap.size() != 0) {
            stateFull(req, login);
            req.setAttribute("errorMap", errorMap);
            HttpUtils.forward(req, resp, "Login", "/WEB-INF/pages/admin/auth/login.jsp");
            return;
        }
        password = StringUtils.MD5(password);
        DaoUser daoUser = (DaoUser) DaoFactory.getDao(DaoTypes.User);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        try {
            user = daoUser.findByLoginAndPassword(user);
            AuthUtils.authUser(req, resp, user, login);
        } catch (DaoException e) {
            HttpUtils.removeSession(req, "authUser");
        }
    }

    private Map<String, String> validateFields(String login, String password) {
        Properties properties = PropertyUtils.getProperties("/settings/error-valid.properties");
        String errorValidateLogin = properties.getProperty("errorValidateLogin");
        String errorValidatePassword = properties.getProperty("errorValidatePassword");

        Map<String, String> errorMap = new HashMap<>();
        if (StringUtils.isBlank(login) || StringUtils.isBlank(login)) {
            errorMap.put("errorValidateLogin", errorValidateLogin);
        }

        if (StringUtils.isEmpty(password) || StringUtils.isBlank(password)) {
            errorMap.put("errorValidatePassword", errorValidatePassword);
        }

        return errorMap;
    }

    private void stateFull(HttpServletRequest req, String login) {
        HttpUtils.setSessionAttribute(req, "login", login);
    }

}
