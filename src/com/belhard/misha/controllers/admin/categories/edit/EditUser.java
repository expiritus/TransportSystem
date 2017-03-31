package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowUser;
import com.belhard.misha.dao.impl.DaoRole;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.AuthUtils;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/user/edit")
public class EditUser extends HttpServlet {

    public static final String URL = "/admin/user/edit";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        if (AuthUtils.closeAccess(req, resp)) {
            return;
        }

        String editUser = req.getParameter("editUser");
        String updateUser = req.getParameter("updateUser");
        DaoUser daoUser = new DaoUser();
        DaoRole daoRole = new DaoRole();

        if(editUser != null){
            int userId = Integer.parseInt(editUser);
            User user = daoUser.findById(User.class, userId);
            user = daoUser.fillRolesByUserId(user);
            System.out.println(user);
            List<Role> roles = daoRole.findAll(Role.class);

            req.setAttribute("allRoles", roles);
            req.setAttribute("user", user);
            HttpUtils.forward(req, resp, "Edit user", "/WEB-INF/pages/admin/categories/edit/user.jsp");
        }else if (updateUser != null){


        }

        HttpUtils.redirect(resp, ShowUser.URL);
    }
}
