package com.belhard.misha.controllers.admin.categories.edit;

import com.belhard.misha.controllers.admin.categories.show.ShowUser;
import com.belhard.misha.dao.factory.DaoFactory;
import com.belhard.misha.dao.factory.DaoTypes;
import com.belhard.misha.dao.impl.DaoRole;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.HttpUtils;
import com.belhard.misha.utils.StringUtils;

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

        String editUser = req.getParameter("editUser");
        String updateUser = req.getParameter("updateUser");
        DaoUser daoUser = (DaoUser) DaoFactory.getDao(DaoTypes.User);
        DaoRole daoRole = (DaoRole) DaoFactory.getDao(DaoTypes.Role);

        if (editUser != null) {
            int userId = Integer.parseInt(editUser);
            User user = daoUser.findById(User.class, userId);
            daoUser.fillRolesToUser(user);
            System.out.println(user);
            List<Role> roles = daoRole.findAll(Role.class);

            req.setAttribute("allRoles", roles);
            req.setAttribute("user", user);
            HttpUtils.forward(req, resp, "Edit user", "/WEB-INF/pages/admin/categories/edit/user.jsp");
        } else if (updateUser != null) {
            int userId = Integer.parseInt(updateUser);
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String[] roles = req.getParameterValues("roles");
            User user = new User();
            user.setId(userId);
            user.setName(name);
            user.setLogin(login);
            user.setEmail(email);
            if (password.length() == 32) {
                user.setPassword(password);
            } else {
                user.setPassword(StringUtils.MD5(password));
            }

            daoUser.update(user, userId);
            daoUser.deleteRolesUser(userId);
            for (String role : roles) {
                int roleId = Integer.parseInt(role);
                daoUser.assignRolesUser(userId, roleId);
            }

        }

        HttpUtils.redirect(resp, ShowUser.URL);
    }
}
