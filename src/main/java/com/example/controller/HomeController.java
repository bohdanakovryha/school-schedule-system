package com.example.controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @RolesAllowed("ROLE_USER")
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

