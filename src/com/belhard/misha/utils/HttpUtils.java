package com.belhard.misha.utils;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public final class HttpUtils {

    private HttpUtils() {
        throw new AssertionError("Class contains static method only. You should not instantiate it!");
    }

    public static void setEncoding(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String encoding = "utf8";
        req.setCharacterEncoding(encoding);
        resp.setCharacterEncoding(encoding);
    }

    public static void forward(HttpServletRequest req, HttpServletResponse resp, String title, String path) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        req.setAttribute("title", title);
        requestDispatcher.forward(req, resp);
    }

    public static void redirect(HttpServletResponse resp, String path) throws ServletException, IOException{
        resp.sendRedirect(path);
    }

    public static void referer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }

    public static void invalidate(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.invalidate();
    }
}
