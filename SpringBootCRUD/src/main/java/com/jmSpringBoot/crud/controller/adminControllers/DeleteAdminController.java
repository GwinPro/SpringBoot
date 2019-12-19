package com.jmSpringBoot.crud.controller.adminControllers;

import com.jmSpringBoot.crud.model.User;
import com.jmSpringBoot.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DeleteAdminController {
    private final UserService userService;

    @Autowired
    public DeleteAdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "admin/delete", method = RequestMethod.GET)
    public String delete(User user) {
        userService.deleteUser(user.getId());
        return "redirect:/admin";
    }
}

