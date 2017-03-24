package com.belhard.misha.controllers.admin.categories.show;

import com.belhard.misha.dao.impl.DaoRole;
import com.belhard.misha.dao.impl.DaoUser;
import com.belhard.misha.entity.Role;
import com.belhard.misha.entity.User;
import com.belhard.misha.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/admin/user")
public class ShowUser extends HttpServlet {

    public static final String URL = "/admin/user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpUtils.setEncoding(req, resp);

        DaoUser daoUser = new DaoUser();
        List<User> users = new ArrayList<>();

        DaoRole daoRole = new DaoRole();
        List<Role> roles = new ArrayList<>();
        try {
            users = daoUser.findAll(User.class);
            for(User user : users){
                daoUser.fillRolesByUserId(user);
            }
            roles = daoRole.findAll(Role.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
        HttpUtils.forward(req, resp, "Users", "/WEB-INF/pages/admin/categories/show/user.jsp");
    }
}
