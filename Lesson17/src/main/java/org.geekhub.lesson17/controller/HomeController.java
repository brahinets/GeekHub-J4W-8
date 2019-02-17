package org.geekhub.lesson17.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final String prop;

    public HomeController(@Value("${property.a}") String prop) {
        this.prop = prop;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("prop", prop);
        return "home";
    }

    @GetMapping(value = "exception")
    public String exception() {
        if (true) {
            throw new RuntimeException("AAAAA");
        }
        return "home";
    }
}
