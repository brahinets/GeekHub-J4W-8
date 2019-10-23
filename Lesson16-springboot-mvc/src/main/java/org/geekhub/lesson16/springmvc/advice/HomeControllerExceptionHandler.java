package org.geekhub.lesson16.springmvc.advice;

import org.geekhub.lesson16.springmvc.controller.HomeController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;

@ControllerAdvice(assignableTypes = HomeController.class)
public class HomeControllerExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({FileNotFoundException.class, RuntimeException.class})
    public ModelAndView exceptionHandler(Exception ex) {
        System.out.println("ERROR!!! " + ex.toString());
        return new ModelAndView("error")
                .addObject("errorMessage", ex.toString());
    }
}