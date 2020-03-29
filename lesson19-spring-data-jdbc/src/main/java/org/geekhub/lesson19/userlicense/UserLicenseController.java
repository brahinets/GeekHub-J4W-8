package org.geekhub.lesson19.userlicense;

import org.geekhub.lesson19.license.License;
import org.geekhub.lesson19.license.LicenseService;
import org.geekhub.lesson19.user.User;
import org.geekhub.lesson19.user.UserService;
import org.geekhub.lesson19.userlicense.UserLicenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.stream.Collectors;

@Controller
public class UserLicenseController {
    private final UserLicenseService userLicenseService;

    public UserLicenseController(UserLicenseService userLicenseService) {
        this.userLicenseService = userLicenseService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userLicenseService.findAllUsers());
        model.addAttribute("licenses", userLicenseService.findAllLicensesForUsers());
        return "home";
    }

    @RequestMapping("add")
    public String add(@RequestParam String username, @RequestParam String firstName, @RequestParam String lastName) {
        userLicenseService.createUserWithLicense(username, firstName, lastName);
        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam Integer id) {
        userLicenseService.deleteByUserId(id);
        return "redirect:/";
    }
}
