package com.belhard.misha.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class SessionUtils {

    private SessionUtils() {
        throw new AssertionError("Class contains static methods only. You should not instantiate it!");
    }

    public static Object getSessionAttribute(HttpServletRequest req, String key) {
        HttpSession session = req.getSession();
        if (session.getAttribute(key) != null) {
            return session.getAttribute(key);
        }
        return null;
    }

    public static void setSessionAttribute(HttpServletRequest req, String key, Object val){
        HttpSession session = req.getSession();
        session.setAttribute(key, val);
    }

    public static void removeSession(HttpServletRequest req, String key){
        HttpSession session = req.getSession();
        session.removeAttribute(key);
    }
}
