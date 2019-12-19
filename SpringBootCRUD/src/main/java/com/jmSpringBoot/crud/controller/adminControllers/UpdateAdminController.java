package com.jmSpringBoot.crud.controller.adminControllers;


import com.jmSpringBoot.crud.model.Role;
import com.jmSpringBoot.crud.model.User;
import com.jmSpringBoot.crud.service.RoleService;
import com.jmSpringBoot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class UpdateAdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UpdateAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @RequestMapping(value = {"/admin/update"}, method = RequestMethod.POST)
    public ModelAndView updatePost(@ModelAttribute("admin/user") User user, HttpServletRequest request) {
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(Long.valueOf(request.getParameter("role"))));
        user.setRoles(roleSet);
        userService.updateUser(user);
        return new ModelAndView("redirect:/admin");
    }

    @RequestMapping(value = {"/admin/update"}, method = RequestMethod.GET)
    public ModelAndView updateGet(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(userId);
        ModelAndView model = new ModelAndView("update");
        model.addObject("user", user);
        return model;
    }
}
