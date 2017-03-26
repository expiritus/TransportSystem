package com.belhard.misha.controllers.admin.categories.add;


import com.belhard.misha.dao.DaoInterface;
import com.belhard.misha.dao.impl.DaoRole;
import com.belhard.misha.entity.AbstractEntity;
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

@WebServlet("/admin/role/add")
public class AddRole extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        String roleParam = req.getParameter("role");

        DaoRole daoRole = new DaoRole();
        Role role = new Role();
        role.setRole(roleParam);
        daoRole.insert(role);

        HttpUtils.referer(req, resp);
    }

}
