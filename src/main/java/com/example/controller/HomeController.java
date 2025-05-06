package com.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SecurityRequirement(name = "oauth2Scheme")
public class HomeController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
