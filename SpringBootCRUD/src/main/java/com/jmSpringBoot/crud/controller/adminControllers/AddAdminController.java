package com.jmSpringBoot.crud.controller.adminControllers;

import com.jmSpringBoot.crud.model.Role;
import com.jmSpringBoot.crud.model.User;
import com.jmSpringBoot.crud.service.RoleService;
import com.jmSpringBoot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AddAdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AddAdminController (UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = {"/admin/add"}, method = RequestMethod.POST)
    public ModelAndView add(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String add = "a user with the same name already exists";
        Set <Role> roles=new HashSet<>();
        String [] rolesFromForm=request.getParameterValues("userRole");
        for (String role:rolesFromForm) {
            roles.add(roleService.getRoleByName(role));
        }
        if (userService.getUserByUserName(user.getName()) == null) {
            user.setRoles(roles);
            userService.addUser(user);
            add = "User added successfully";
        }
        redirectAttributes.addFlashAttribute("addResult", add);
        return new ModelAndView("redirect:/admin");
    }
}
