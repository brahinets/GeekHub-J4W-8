package org.geekhub.lesson16.springmvc.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.logging.Logger;

@Component
public class ControllerLogInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = Logger.getLogger(getClass().getSimpleName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof HandlerMethod) {
            logger.info(
                    "[" + LocalDateTime.now() + "]: [" + ((HandlerMethod) handler).getBeanType() + "]"
                            + "   -   START:    " + request.getMethod() + " " + request.getRequestURI()
            );
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (handler instanceof HandlerMethod) {
            if (Objects.nonNull(ex)) {
                logger.info("[" + LocalDateTime.now() + "]: ["
                        + ((HandlerMethod) handler).getBeanType() + "]   -   ERROR:    "
                        + request.getMethod() + " " + request.getRequestURI() + ": " + ex.toString());
                response.sendRedirect("error.html");
            } else {
                logger.info("[" + LocalDateTime.now() + "]: ["
                        + ((HandlerMethod) handler).getBeanType() + "]   -   COMPLETE: "
                        + request.getMethod() + " " + request.getRequestURI());
            }
        }
    }
}
