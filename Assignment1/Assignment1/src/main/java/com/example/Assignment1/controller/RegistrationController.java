package com.example.Assignment1.controller;

import com.example.Assignment1.model.Role;
import com.example.Assignment1.model.User;
import com.example.Assignment1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    private final UserService svc;

    public RegistrationController(UserService svc) {
        this.svc = svc;
    }

    @GetMapping("/register")
    public String form(Model m) {
        m.addAttribute("user", new User());
        m.addAttribute("roles", Role.values());
        return "register";
    }

    @PostMapping("/register")
    public String submit(@Valid @ModelAttribute User user,BindingResult r) {
        if (r.hasErrors()) return "register";
        svc.saveUser(user);
        return "redirect:/login?registered";
    }
}