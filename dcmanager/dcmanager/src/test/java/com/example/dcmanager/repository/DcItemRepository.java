package com.example.dcmanager.repository;

import com.example.dcmanager.model.Brand;
import com.example.dcmanager.model.DcItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DcItemRepository extends JpaRepository<DcItem, Long> {
    List<DcItem> findByNameAndBrandAndQuantityGreaterThan(String name, Brand brand, int quantity);
}
