package org.geekhub.lesson19.user;

import org.geekhub.lesson19.license.License;
import org.geekhub.lesson19.user.User;
import org.geekhub.lesson19.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.Collections;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "home";
    }

    @RequestMapping("add")
    public String add(@ModelAttribute User user) {
        final License license = new License("Licence " + LocalTime.now());
        user.setLicenses(Collections.singletonList(license));
        license.setUser(user);
        userService.save(user);
        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return "redirect:/";
    }
}
