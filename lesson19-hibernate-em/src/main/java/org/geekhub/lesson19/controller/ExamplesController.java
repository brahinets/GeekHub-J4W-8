package org.geekhub.lesson19.controller;

import org.geekhub.lesson19.db.persistence.License;
import org.geekhub.lesson19.db.persistence.User;
import org.geekhub.lesson19.service.UserLicenseService;
import org.geekhub.lesson19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;

@Controller
public class ExamplesController {
    private final UserService userService;
    private final UserLicenseService userLicenseService;

    public ExamplesController(UserService userService, UserLicenseService userLicenseService) {
        this.userService = userService;
        this.userLicenseService = userLicenseService;
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

        userLicenseService.save(user, new License("Licence " + LocalTime.now()));
        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam Integer id) {
        userService.findBy(id).ifPresent(userLicenseService::delete);
        return "redirect:/";
    }
}
