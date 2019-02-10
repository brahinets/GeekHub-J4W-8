package org.geekhub.lesson16.springmvc;

import org.geekhub.lesson16.springmvc.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class ExampleRestController {
    @GetMapping("rest")
    public List<String> rest() {
        return Arrays.asList("str1", "str2", "str3", "qwerty");
    }

    @GetMapping("user-from-form")
    @ResponseStatus(HttpStatus.OK)
    public UserDto modelAttribute(@ModelAttribute UserDto user) {
        return user;
    }

    @GetMapping("username/{username}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String pathVariable(@PathVariable String username) {
        return username;
    }

    @GetMapping("username")
    public String requestParam(@RequestParam String username) {
        return username;
    }

    @PostMapping("user-from-body")
    public UserDto requestBody(@RequestBody UserDto user) {
        return user;
    }

    @GetMapping("put-to-session")
    public void toSession(@RequestParam String data, HttpServletRequest request) {
        request.getSession().setAttribute("data", data);
    }

    @GetMapping("get-from-session")
    public String fromSession(@SessionAttribute String data) {
        return data;
    }

    @GetMapping("redirect-me")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("https://google.com");
    }

    @RequestMapping(value = "exception")
    public String exception() {
        if (true) {
            throw new RuntimeException("ERROR");
        }
        return "mav-view";
    }
}
