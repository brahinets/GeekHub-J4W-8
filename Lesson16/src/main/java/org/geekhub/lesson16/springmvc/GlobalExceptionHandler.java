package org.geekhub.lesson16.springmvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = "org.geekhub.lesson16.springmvc")
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception ex) {
        ModelAndView mav = new ModelAndView("serviceException");
        mav.addObject("errorMessage", "Some Message. " + ex.toString());
        return mav;
    }
}