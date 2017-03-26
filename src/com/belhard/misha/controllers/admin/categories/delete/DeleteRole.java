package com.belhard.misha.controllers.admin.categories.delete;

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


@WebServlet("/admin/role/delete")
public class DeleteRole extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        int roleId = Integer.parseInt(req.getParameter("roleId"));

        DaoRole daoRole = new DaoRole();
        daoRole.delete(Role.class, roleId);

        HttpUtils.referer(req, resp);
    }
}
