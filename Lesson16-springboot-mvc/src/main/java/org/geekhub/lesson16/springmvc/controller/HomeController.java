package org.geekhub.lesson16.springmvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class HomeController {
    private final String constructorInjectProp;
    @Value("${property.a}") private String fieldInjectProp;

    public HomeController(@Value("${property.b}") String constructorInjectProp) {
        this.constructorInjectProp = constructorInjectProp;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("fieldProp", fieldInjectProp);
        model.addAttribute("constProp", constructorInjectProp);
        return "home";
    }

    @GetMapping(value = "string")
    public String byStringViewName() {
        return "home";
    }

    @RequestMapping(value = "mav")
    public ModelAndView byModelAndView() {
        final ModelAndView mav = new ModelAndView("mav-view");
        mav.addObject("serverDate", LocalDate.now());
        mav.addObject("prop", constructorInjectProp);
        return mav;
    }

    @RequestMapping(value = "in-mav", method = RequestMethod.GET)
    public ModelAndView byInputModelAndView(ModelAndView mav) {
        mav.setViewName("mav-view");
        mav.addObject("serverDate", LocalDate.now());
        return mav;
    }

    @RequestMapping(value = "in-model", method = {RequestMethod.GET, RequestMethod.POST})
    public String byInputModel(Model model) {
        model.addAttribute("serverDate", LocalDate.now());
        return "mav-view";
    }

    @GetMapping(value = "rest")
    @ResponseBody
    public LocalDateTime restEndpoint() {
        return LocalDateTime.now();
    }

    @RequestMapping(value = "exception")
    public String exception() {
        if (true) {
            throw new RuntimeException("ERROR");
        }
        return "home";
    }
}
