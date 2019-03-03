package org.geekhub.lesson19.controller;

import org.geekhub.lesson19.db.persistence.User;
import org.geekhub.lesson19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExamplesController {
    private final UserService userService;

    @Autowired
    public ExamplesController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "home";
    }

    @RequestMapping("add")
    public String add(@RequestParam String username, @RequestParam String firstName, @RequestParam String lastName) {
        final User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        final User savedUser = userService.save(user);
        System.out.println("new user id: " + savedUser.getId());

        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam Integer id) {
        userService.findBy(id).ifPresent(userService::delete);
        return "redirect:/";
    }
}
