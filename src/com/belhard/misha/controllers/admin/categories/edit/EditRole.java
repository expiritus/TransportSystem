package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowRole;
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


@WebServlet("/admin/role/edit")
public class EditRole extends HttpServlet {

    public static final String URL = "/admin/role/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if(AuthUtils.closeAccess(req, resp)){
            return;
        }

        String editRole = req.getParameter("editRole");
        String updateRole = req.getParameter("updateRole");
        DaoRole daoRole = new DaoRole();
        if(editRole != null){
            int editRoleId = Integer.parseInt(editRole);
            Role role = daoRole.findById(Role.class, editRoleId);
            req.setAttribute("role", role);
            HttpUtils.forward(req, resp, "Edit role", "/WEB-INF/pages/admin/categories/edit/role.jsp");
            return;
        }else if (updateRole != null){
            int updateRoleId = Integer.parseInt(updateRole);
            String roleParam = req.getParameter("role");
            Role role = new Role();
            role.setId(updateRoleId);
            role.setRole(roleParam);
            daoRole.update(role, updateRoleId);
        }

        HttpUtils.redirect(resp, ShowRole.URL);
    }
}
