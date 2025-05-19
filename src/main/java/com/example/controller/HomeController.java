package com.example.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @RolesAllowed("ROLE_USER")
    @GetMapping("/")
    public String index() {
        return "index"; // index.html (розташований у /templates або /static)
    }

}

