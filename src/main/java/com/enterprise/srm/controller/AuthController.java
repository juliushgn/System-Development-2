package com.enterprise.srm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/")
    public String root(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) return "redirect:/login";
        String role = auth.getAuthorities().iterator().next().getAuthority();
        return switch (role) {
            case "ROLE_MANAGER"       -> "redirect:/manager/dashboard";
            case "ROLE_SERVICE_AGENT" -> "redirect:/agent/requests";
            default                   -> "redirect:/employee/requests";
        };
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
