package com.belhard.misha.controllers.admin.auth;


import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;
import com.belhard.misha.utils.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    public static final String URL = "registration";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);


        HttpUtils.forward(req, resp, "Registration", "/WEB-INF/pages/admin/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = StringUtils.MD5(req.getParameter("password"));
        String repeatPassword = StringUtils.MD5(req.getParameter("repeatPassword"));
        int countValidFields = validateRequiredFields(req, name, login, email, password, repeatPassword);
        if(countValidFields < 4){
            stateFull(req, name, login, email);
            HttpUtils.referer(req, resp);
            return;
        }

        DaoUser daoUser = new DaoUser();
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        try {
            int idUser = daoUser.insert(user);
            user.setId(idUser);
            AuthUtils.authUser(req, resp, user, user.getLogin());
        } catch (SQLException e) {
            req.setAttribute("failSaveUser", "Пользователь с таким логином/email уже существует");
            stateFull(req, name, login, email);
            HttpUtils.forward(req, resp, "Fail save user", "/WEB-INF/pages/admin/registration.jsp");
        }

    }

    private void stateFull(HttpServletRequest req, String name, String login, String email){
        HttpUtils.setSessionAttribute(req,"name", name);
        HttpUtils.setSessionAttribute(req, "login", login);
        HttpUtils.setSessionAttribute(req,"email", email);
    }

    private int validateRequiredFields(HttpServletRequest req, String name, String login, String email, String password, String repeatPassword){
        String requiredText = "Поле обязательно для заполнения";
        String notMatchPasswordText = "Пароли не совпадают";
        String notValidEmailText = "Введите пожалуйста валидный Email";
        int countValidFields = 0;
        if(StringUtils.isEmpty(name) || StringUtils.isBlank(name)){
            HttpUtils.setSessionAttribute(req, "errorValidName", requiredText);
        }else {
            HttpUtils.invalidateByAttribute(req, "errorValidName");
            countValidFields++;
        }

        if(StringUtils.isEmpty(login) || StringUtils.isBlank(login)){
            HttpUtils.setSessionAttribute(req, "errorValidLogin", requiredText);
        }else {
            HttpUtils.invalidateByAttribute(req, "errorValidLogin");
            countValidFields++;
        }

        if(StringUtils.isBlank(email) || StringUtils.isBlank(email)){
            HttpUtils.setSessionAttribute(req, "errorValidEmail", requiredText);
        }else {
            if(!EmailValidator.getInstance().isValid(email)){
                HttpUtils.setSessionAttribute(req, "errorValidEmail", notValidEmailText);
            }else {
                HttpUtils.invalidateByAttribute(req, "errorValidEmail");
                countValidFields++;
            }
        }

        if(!password.equals(repeatPassword)){
            HttpUtils.setSessionAttribute(req, "errorMatchPassword", notMatchPasswordText);
        }else {
            HttpUtils.invalidateByAttribute(req, "errorMatchPassword");
            countValidFields++;
        }

        return countValidFields;

    }
}
