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
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {

    public static final String URL = "/registration";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpUtils.forward(req, resp, "Registration", "/WEB-INF/pages/admin/auth/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        Map<String, String> errorMap = validateRequiredFields(name, login, email, password, repeatPassword);
        if(errorMap.size() != 0){
            Map<String, String> stateFullMap =  stateFull(name, login, email, password, repeatPassword);
            req.setAttribute("errorMap", errorMap);
            req.setAttribute("stateFullMap", stateFullMap);
            HttpUtils.forward(req, resp, "Registration", "/WEB-INF/pages/admin/auth/registration.jsp");
            return;
        }

        DaoUser daoUser = (DaoUser) DaoFactory.getDao(DaoTypes.User);
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(StringUtils.MD5(password));
        try {
            int idUser = daoUser.insert(user);
            user.setId(idUser);
            String ref = req.getHeader("referer");
            if(ref.contains("/admin")){
                String[] rolesParam = req.getParameterValues("roles");
                for(String idRole : rolesParam){
                    daoUser.assignRolesUser(idUser, Integer.parseInt(idRole));
                }
                HttpUtils.referer(req, resp);
                return;
            }
            daoUser.assignRoleUser(user);
            AuthUtils.authUser(req, resp, user, user.getLogin());
        } catch (DaoException e) {
            Properties properties = PropertyUtils.getProperties("/settings/error-valid.properties");
            String failSaveUser = properties.getProperty("failSaveUser");
            req.setAttribute("failSaveUser", failSaveUser);
            Map<String, String> stateFillMap = stateFull(name, login, email, password, repeatPassword);
            req.setAttribute("stateFullMap", stateFillMap);
            HttpUtils.forward(req, resp, "Fail save user", "/WEB-INF/pages/admin/auth/registration.jsp");
        }

    }

    private Map<String, String> stateFull(String name, String login, String email, String password, String repeatPassword){
        Map<String, String> stateFull = new HashMap<>();
        stateFull.put("name", name);
        stateFull.put("login", login);
        stateFull.put("email", email);
        stateFull.put("password", password);
        stateFull.put("repeatPassword", repeatPassword);
        return stateFull;
    }


    private Map<String, String> validateRequiredFields(String name, String login, String email, String password, String repeatPassword){
        Properties validSettings = PropertyUtils.getProperties("/settings/error-valid.properties");
        String errorValidName = validSettings.getProperty("errorValidName");
        String errorValidLogin = validSettings.getProperty("errorValidLogin");
        String errorValidEmail = validSettings.getProperty("errorValidEmail");
        String errorEmptyEmail = validSettings.getProperty("errorEmptyEmail");
        String errorMatchPassword = validSettings.getProperty("errorMatchPassword");
        String errorLengthPassword = validSettings.getProperty("errorLengthPassword");
        String errorValidatePassword = validSettings.getProperty("errorValidatePassword");

        Map<String, String> errorMap = new HashMap<>();
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(name)){
            errorMap.put("errorValidName", errorValidName);
        }

        if(StringUtils.isEmpty(login) || StringUtils.isBlank(login)){
            errorMap.put("errorValidLogin", errorValidLogin);
        }

        if(StringUtils.isEmpty(email) || StringUtils.isBlank(email)){
            errorMap.put("errorValidEmail", errorEmptyEmail);
        }else {
            if(!EmailValidator.getInstance().isValid(email)){
                errorMap.put("errorValidEmail", errorValidEmail);
            }
        }

        if(StringUtils.isEmpty(password) || StringUtils.isBlank(password)){
            errorMap.put("errorMatchPassword", errorValidatePassword);
        }else{
            if(!password.equals(repeatPassword)){
                errorMap.put("errorMatchPassword", errorMatchPassword);
            }else {
                char[] chars = errorLengthPassword.toCharArray();
                int lengthPassword = 0;
                for(char c : chars){
                    if(Character.isDigit(c)){
                        lengthPassword = Character.getNumericValue(c);
                    }
                }
                if(password.length() < lengthPassword){
                    errorMap.put("errorMatchPassword", errorLengthPassword);
                }
            }
        }

        return errorMap;
    }
}
