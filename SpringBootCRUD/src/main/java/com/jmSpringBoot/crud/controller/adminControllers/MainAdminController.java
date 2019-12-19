package com.jmSpringBoot.crud.controller.adminControllers;

import com.jmSpringBoot.crud.model.User;
import com.jmSpringBoot.crud.service.RoleService;
import com.jmSpringBoot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainAdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String MainAdminPageGet() {
        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView mainAdminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.addObject("listUser", this.userService.getAllClient());
        modelAndView.addObject("rolesName", roleService.getRoles());
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
