package com.example.Assignment1.service;

import com.example.Assignment1.model.Brand;
import com.example.Assignment1.model.Item;
import com.example.Assignment1.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {
    private final ItemRepository repo;

    public ItemService(ItemRepository repo) {
        this.repo = repo;
    }

    public void save(Item item) {
        repo.save(item);
    }

    public Page<Item> findAll(Pageable p) {
        return repo.findAll(p);
    }

    public Page<Item> findByBrandAndYear(Brand b,int y,Pageable p) {
        return repo.findByBrandAndYearOfCreation(b,y,p);
    }

    public List<Item> findAllAllItems() {
        return repo.findAll();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}