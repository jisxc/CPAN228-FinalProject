package com.example.dcmanager.controller;

import com.example.dcmanager.model.Brand;
import com.example.dcmanager.model.DcItem;
import com.example.dcmanager.service.DistributionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/distribution-centres")
public class DistributionCentreController {
    private final DistributionService svc;

    public DistributionCentreController(DistributionService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<?> getCentres() {
        return svc.listCentres();
    }

    @PostMapping("/{centreId}/items")
    public DcItem addItem(@PathVariable Long centreId, @Valid @RequestBody DcItem item) {
        return svc.addItem(centreId, item);
    }

    @DeleteMapping("/{centreId}/items/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long centreId, @PathVariable Long itemId) {
        svc.deleteItem(centreId, itemId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/request")
    public DcItem requestItem(@RequestParam String name, @RequestParam Brand brand) {
        return svc.requestItem(name, brand);
    }
}
