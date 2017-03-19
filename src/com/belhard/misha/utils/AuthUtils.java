package com.belhard.misha.utils;

import com.belhard.misha.controllers.admin.IndexController;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public final class AuthUtils {

    private AuthUtils() {
        throw new InstantiationError("Class contains static methods only. You should not instantiate it!");
    }

    public static int authUser(HttpServletRequest req, HttpServletResponse resp, User user, String login) throws ServletException, IOException {
        if (user.getId() == 0) {
            HttpUtils.setSessionAttribute(req, "login", login);
            HttpUtils.setSessionAttribute(req, "userNotFound", "Не верный логин/пароль");
            HttpUtils.referer(req, resp);
            return 0;
        }
        HttpUtils.invalidateByAttribute(req, "userNotFound");
        HttpUtils.invalidateByAttribute(req, login);
        HttpUtils.setSessionAttribute(req, "authUser", user);
        DaoUser daoUser = new DaoUser();
        try {
            user = daoUser.getRolesByUserId(user);
            for(Role role : user.getRoles()){
                if(role.getRole().equals("admin") || role.getRole().equals("manager")){
                    HttpUtils.redirect(resp, IndexController.URL);
                    return user.getId();
                }
            }
            HttpUtils.redirect(resp, "index.html");
            return user.getId();
        } catch (SQLException e) {
            throw new RuntimeException("Can not authorize user");
        }

    }
}
