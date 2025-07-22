package com.example.Assignment1.controller;

import com.example.Assignment1.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/items")
public class AdminController {
    private final ItemService svc;

    public AdminController(ItemService svc) {
        this.svc = svc;
    }

    @GetMapping
    public String manage(Model m) {
        m.addAttribute("items", svc.findAllAllItems());
        return "admin-items";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        svc.deleteById(id);
        return "redirect:/admin/items";
    }
}