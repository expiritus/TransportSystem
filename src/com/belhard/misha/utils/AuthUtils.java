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
import java.util.Properties;

public final class AuthUtils {

    private AuthUtils() {
        throw new InstantiationError("Class contains static methods only. You should not instantiate it!");
    }

    public static void authUser(HttpServletRequest req, HttpServletResponse resp, User user, String login) throws ServletException, IOException {
        if (user.getId() == 0) {
            Properties properties = PropertyUtils.getValidProperties();
            String userNotFound = properties.getProperty("userNotFound");
            HttpUtils.setSessionAttribute(req, "login", login);
            HttpUtils.setSessionAttribute(req, "userNotFound", userNotFound);
            HttpUtils.referer(req, resp);
            return;
        }
        HttpUtils.invalidateSessionByAttribute(req, "userNotFound");
        HttpUtils.invalidateSessionByAttribute(req, login);
        DaoUser daoUser = new DaoUser();
        try {
            user = daoUser.getRolesByUserId(user);
            HttpUtils.setSessionAttribute(req, "authUser", user);
            checkUserRole(resp, user);
        } catch (SQLException e) {
            throw new RuntimeException("Can not authorize user");
        }

    }

    public static void checkUserRole(HttpServletResponse resp, User user) throws ServletException, IOException {
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("admin") || role.getRole().equals("manager")) {
                HttpUtils.redirect(resp, IndexController.URL);
                return;
            }
        }
        HttpUtils.redirect(resp, "index.html");
        return;
    }

}
