package com.belhard.misha.controllers.admin.auth;


import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;
import com.belhard.misha.utils.PropertyUtils;
import com.belhard.misha.utils.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    public static final String URL = "registration";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);


        HttpUtils.forward(req, resp, "Registration", "/WEB-INF/pages/admin/auth/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        int countValidFields = validateRequiredFields(req, name, login, email, password, repeatPassword);
        if(countValidFields < 4){
            stateFull(req, name, login, email, password, repeatPassword);
            HttpUtils.referer(req, resp);
            return;
        }

        DaoUser daoUser = new DaoUser();
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(StringUtils.MD5(password));
        try {
            int idUser = daoUser.insert(user);
            user.setId(idUser);
            daoUser.assignRoleUser(user);
            AuthUtils.authUser(req, resp, user, user.getLogin());
        } catch (SQLException e) {
            Properties properties = PropertyUtils.getValidProperties();
            String failSaveUser = properties.getProperty("failSaveUser");
            req.setAttribute("failSaveUser", failSaveUser);
            stateFull(req, name, login, email, password, repeatPassword);
            HttpUtils.forward(req, resp, "Fail save user", "/WEB-INF/pages/admin/auth/registration.jsp");
        }

    }

    private void stateFull(HttpServletRequest req, String name, String login, String email, String password, String repeatPassword){
        HttpUtils.setSessionAttribute(req,"name", name);
        HttpUtils.setSessionAttribute(req, "login", login);
        HttpUtils.setSessionAttribute(req,"email", email);
        HttpUtils.setSessionAttribute(req, "password", password);
        HttpUtils.setSessionAttribute(req, "repeatPassword", repeatPassword);
    }

    private int validateRequiredFields(HttpServletRequest req, String name, String login, String email, String password, String repeatPassword){
        Properties validSettings = PropertyUtils.getValidProperties();
        String errorValidName = validSettings.getProperty("errorValidName");
        String errorValidLogin = validSettings.getProperty("errorValidLogin");
        String errorValidEmail = validSettings.getProperty("errorValidEmail");
        String errorEmptyEmail = validSettings.getProperty("errorEmptyEmail");
        String errorMatchPassword = validSettings.getProperty("errorMatchPassword");
        String errorLengthPassword = validSettings.getProperty("errorLengthPassword");

        int countValidFields = 0;
        if(StringUtils.isEmpty(name) || StringUtils.isBlank(name)){
            HttpUtils.setSessionAttribute(req, "errorValidName", errorValidName);
        }else {
            HttpUtils.invalidateSessionByAttribute(req, "errorValidName");
            countValidFields++;
        }

        if(StringUtils.isEmpty(login) || StringUtils.isBlank(login)){
            HttpUtils.setSessionAttribute(req, "errorValidLogin", errorValidLogin);
        }else {
            HttpUtils.invalidateSessionByAttribute(req, "errorValidLogin");
            countValidFields++;
        }

        if(StringUtils.isBlank(email) || StringUtils.isBlank(email)){
            HttpUtils.setSessionAttribute(req, "errorValidEmail", errorEmptyEmail);
        }else {
            if(!EmailValidator.getInstance().isValid(email)){
                HttpUtils.setSessionAttribute(req, "errorValidEmail", errorValidEmail);
            }else {
                HttpUtils.invalidateSessionByAttribute(req, "errorValidEmail");
                countValidFields++;
            }
        }


        if(!password.equals(repeatPassword)){
            HttpUtils.setSessionAttribute(req, "errorMatchPassword", errorMatchPassword);
        }else {
            char[] chars = errorLengthPassword.toCharArray();
            int lengthPassword = 0;
            for(char c : chars){
                if(Character.isDigit(c)){
                    lengthPassword = Character.getNumericValue(c);
                }
            }
            if(password.length() < lengthPassword){
                HttpUtils.setSessionAttribute(req, "errorMatchPassword", errorLengthPassword);
            }else {
                HttpUtils.invalidateSessionByAttribute(req, "errorMatchPassword");
                countValidFields++;
            }
        }

        return countValidFields;
    }



}
