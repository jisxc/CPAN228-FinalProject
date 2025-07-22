package com.example.Assignment1.repository;

import com.example.Assignment1.model.Brand;
import com.example.Assignment1.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByBrandAndYearOfCreation(Brand brand, int year, Pageable pageable);
}
