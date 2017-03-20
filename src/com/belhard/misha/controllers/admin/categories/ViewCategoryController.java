package com.belhard.misha.controllers.admin.categories;


import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/*")
public class ViewCategoryController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);


        HttpUtils.forward(req, resp, "Countries", "/WEB-INF/pages/admin/categories/view-category.jsp");
    }
}
