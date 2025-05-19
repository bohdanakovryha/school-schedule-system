package com.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

/*
package com.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html
    }

    @GetMapping("/login/start")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8081/realms/school-scheduler/protocol/openid-connect/auth" +
                "?client_id=spring-app" +
                "&response_type=code" +
                "&scope=openid" +
                "&redirect_uri=http://localhost:8080/index.html"); // порт 8080 - твій додаток
    }

}*/
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // повертає login.html (Thymeleaf)
    }

   /* @GetMapping("/login/start")
    public void login(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8081/realms/school-scheduler/protocol/openid-connect/auth" +
                "?client_id=spring-app" +
                "&response_type=code" +
                "&scope=openid" +
                "&redirect_uri=http://localhost:8080/index.html"); // Ось сюди повернеться після логіну
    }*/

}
