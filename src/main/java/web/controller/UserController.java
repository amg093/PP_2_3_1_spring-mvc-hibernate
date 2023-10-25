package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final String REDIRECT_MAIN = "redirect:allUsers";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("version 5.3.30 from Sep 14, 2023");
        model.addAttribute("messages", messages);
        return "index";
    }
    @GetMapping(value = "/allUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping(value = "/addUser")
    public String showAddUserPage(@ModelAttribute("user") User user) {
        return "addUser";
    }

    @PostMapping(value = "/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return REDIRECT_MAIN;
    }

    @GetMapping(value = "/editUser")
    public String showEditUserPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping(value = "/editUser")
    public String editUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return REDIRECT_MAIN;
    }

    @GetMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return REDIRECT_MAIN;
    }

}
