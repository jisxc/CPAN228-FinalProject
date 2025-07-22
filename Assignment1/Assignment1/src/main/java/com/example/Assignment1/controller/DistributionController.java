package com.example.Assignment1.controller;

import com.example.Assignment1.model.Brand;
import com.example.Assignment1.model.Item;
import com.example.Assignment1.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/admin/distribution")
public class DistributionController {
    private static final Logger log = LoggerFactory.getLogger(DistributionController.class);

    private final RestTemplate rest;
    private final ItemService svc;

    @Value("${dc.service.url}")
    private String dcServiceUrl;

    public DistributionController(ItemService svc, RestTemplateBuilder builder) {
        this.svc  = svc;
        this.rest = builder
                .basicAuthentication("manager", "managerpass")
                .build();
    }

    @GetMapping
    public String showForm(Model m) {
        m.addAttribute("brands", Brand.values());
        DCentre[] centres = rest.getForObject(dcServiceUrl + "/distribution-centres", DCentre[].class);
        m.addAttribute("centres", centres);
        return "distribution-form";
    }

    @PostMapping("/request")
    public String requestItem(@RequestParam String name,
                              @RequestParam Brand brand,
                              Model m) {
        String url = String.format("%s/distribution-centres/request?name=%s&brand=%s",
                dcServiceUrl, name, brand);
        try {
            DcResponse resp = rest.getForObject(url, DcResponse.class);
            Item item = new Item();
            item.setName(resp.getName());
            item.setBrand(resp.getBrand());
            item.setYearOfCreation(resp.getYearOfCreation());
            item.setPrice(resp.getPrice());
            svc.save(item);
            return "redirect:/admin/distribution?success";
        } catch (RestClientException e) {
            log.error("DC API call failed: {}", e.getMessage(), e);
            m.addAttribute("error", "Unable to replenish stock: " + e.getMessage());
            return "distribution-error";
        }
    }

    private static class DCentre {
        private Long id;
        private String name;
        private double latitude;
        private double longitude;
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public double getLatitude() { return latitude; }
        public void setLatitude(double latitude) { this.latitude = latitude; }
        public double getLongitude() { return longitude; }
        public void setLongitude(double longitude) { this.longitude = longitude; }
    }

    private static class DcResponse {
        private Long centreId;
        private String name;
        private Brand brand;
        private int yearOfCreation;
        private double price;
        public Long getCentreId() { return centreId; }
        public void setCentreId(Long centreId) { this.centreId = centreId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Brand getBrand() { return brand; }
        public void setBrand(Brand brand) { this.brand = brand; }
        public int getYearOfCreation() { return yearOfCreation; }
        public void setYearOfCreation(int yearOfCreation) { this.yearOfCreation = yearOfCreation; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }
}
