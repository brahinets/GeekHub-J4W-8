package org.geekhub.lesson19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends WebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
