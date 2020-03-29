package org.geekhub.lesson19.userlicense;

import org.geekhub.lesson19.license.License;
import org.geekhub.lesson19.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;

@Controller
public class UserLicenseController {
    private final UserLicenseService userLicenseService;

    public UserLicenseController(UserLicenseService userLicenseService) {
        this.userLicenseService = userLicenseService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userLicenseService.findAllUsers());
        return "home";
    }

    @RequestMapping("add")
    public String add(@ModelAttribute User user) {
        License license = new License();
        license.setName("Licence " + LocalTime.now());
        userLicenseService.save(user, license);
        return "redirect:/";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam Integer id) {
        userLicenseService.deleteUserById(id);
        return "redirect:/";
    }
}
