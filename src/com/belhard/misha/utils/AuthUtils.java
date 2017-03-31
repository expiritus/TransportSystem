package com.belhard.misha.utils;

import com.belhard.misha.controllers.admin.IndexController;
import com.belhard.misha.controllers.admin.auth.LoginController;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

public final class AuthUtils {

    private AuthUtils() {
        throw new InstantiationError("Class contains static methods only. You should not instantiate it!");
    }

    public static void authUser(HttpServletRequest req, HttpServletResponse resp, User user, String login) throws ServletException, IOException {
        if (user.getId() == 0) {
            Properties properties = PropertyUtils.getProperties("/settings/error-valid.properties");
            String userNotFound = properties.getProperty("userNotFound");
            HttpUtils.setSessionAttribute(req, "login", login);
            HttpUtils.setSessionAttribute(req, "userNotFound", userNotFound);
            HttpUtils.referer(req, resp);
            return;
        }
        HttpUtils.invalidateSessionByAttribute(req, "userNotFound");
        HttpUtils.invalidateSessionByAttribute(req, login);
        DaoUser daoUser = new DaoUser();
        user = daoUser.fillRolesToUser(user);
        HttpUtils.setSessionAttribute(req, "authUser", user);
        checkAndProvideUserByRole(req, resp, user);

    }

    public static void checkAndProvideUserByRole(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("admin") || role.getRole().equals("manager")) {
                HttpUtils.redirect(resp, req.getContextPath() + "" + IndexController.URL);
                return;
            }
        }
        HttpUtils.redirect(resp, req.getContextPath() + "" + com.belhard.misha.controllers.main.IndexController.MAIN_URL);
        return;
    }

    public static boolean closeAccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (HttpUtils.getSessionAttribute(req, "authUser") == null) {
            HttpUtils.redirect(resp, req.getContextPath() + "" + LoginController.URL);
            return true;
        }
        return false;
    }

    public static boolean isAdmin(User user) {
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("admin")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isManager(User user) {
        for (Role role : user.getRoles()) {
            if (role.getRole().equals("manager")) {
                return true;
            }
        }
        return false;
    }

}
