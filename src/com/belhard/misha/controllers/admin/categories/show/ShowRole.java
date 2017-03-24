package com.belhard.misha.controllers.admin.categories.show;


import com.belhard.misha.dao.impl.DaoRole;
import com.belhard.misha.entity.Role;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/role")
public class ShowRole extends HttpServlet {

    public static final String URL = "/admin/role";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        DaoRole daoRole = new DaoRole();
        List<Role> roles = null;
        try {
            roles = daoRole.findAll(Role.class);
        } catch (SQLException e) {
            throw new RuntimeException("Can not find roles", e);
        }

        req.setAttribute("roles", roles);
        HttpUtils.forward(req, resp, "Roles", "/WEB-INF/pages/admin/categories/show/role.jsp");

    }
}
