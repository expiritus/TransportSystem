package com.belhard.misha.controllers.admin.categories.show;


import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
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
import java.util.List;

@WebServlet("/admin/role")
public class ShowRole extends HttpServlet {

    public static final String URL = "/admin/role";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoRole daoRole = (DaoRole) DaoFactory.getDao(DaoTypes.Role);
        List<Role> roles = daoRole.findAll(Role.class);

        req.setAttribute("roles", roles);
        HttpUtils.forward(req, resp, "Roles", "/WEB-INF/pages/admin/categories/show/role.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String addRole = req.getParameter("addRole");
        String deleteRole = req.getParameter("deleteRole");

        DaoRole daoRole = (DaoRole) DaoFactory.getDao(DaoTypes.Role);

        if(addRole != null){
            String roleParam = req.getParameter("role");
            Role role = new Role();
            role.setRole(roleParam);
            daoRole.insert(role);
        }else if (deleteRole != null){
            int roleId = Integer.parseInt(req.getParameter("deleteRole"));
            daoRole.delete(Role.class, roleId);
        }

        HttpUtils.redirect(resp, req.getContextPath() + ShowRole.URL);
    }
}
