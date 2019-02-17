package org.geekhub.lesson17.controller;

import org.geekhub.lesson17.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class HomeRestController {
    @GetMapping("rest")
    public List<String> rest() {
        return List.of("str1", "str2", "str3", "qwerty");
    }

    @GetMapping("user-from-form")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto modelAttribute(@ModelAttribute UserDto user) {
        return user;
    }

    @PostMapping("user-from-body")
    public UserDto requestBody(@RequestBody UserDto user) {
        return user;
    }

    @GetMapping("put-to-session")
    public void toSession(@RequestParam String data, HttpSession session) {
        session.setAttribute("data", data);
    }

    @GetMapping("get-from-session")
    public String fromSession(@SessionAttribute String data) {
        return data;
    }
}
