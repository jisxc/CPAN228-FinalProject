package com.example.Assignment1.controller;

import com.example.Assignment1.model.Brand;
import com.example.Assignment1.model.Item;
import com.example.Assignment1.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Brand.values());
        return "item-form";
    }

    @PostMapping
    public String addItem(@Valid @ModelAttribute Item item, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("brands", Brand.values());
            return "item-form";
        }
        service.save(item);
        return "redirect:/items/list?success";
    }

    @GetMapping("/list")
    public String listItems(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Brand brand,
            @RequestParam(required = false) Integer year
    ) {
        Pageable pageable = (sort != null && sort.equals("brand"))
                ? PageRequest.of(page, size, Sort.by("brand"))
                : PageRequest.of(page, size);

        var items = (brand != null && year != null)
                ? service.findByBrandAndYear(brand, year, pageable)
                : service.findAll(pageable);

        model.addAttribute("items", items);
        model.addAttribute("brands", Brand.values());
        model.addAttribute("selectedBrand", brand);
        model.addAttribute("selectedYear", year);
        return "item-list";
    }
}
