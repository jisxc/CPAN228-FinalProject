package com.example.Assignment1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Brand is required")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Min(value = 2022, message = "Year must be 2022 or later")
    private int yearOfCreation;

    @Min(value = 1001, message = "Price must be more than 1000")
    private double price;

    public Item() {
    }

    public Item(String name, Brand brand, int yearOfCreation, double price) {
        this.name = name;
        this.brand = brand;
        this.yearOfCreation = yearOfCreation;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getYearOfCreation() {
        return yearOfCreation;
    }
    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand=" + brand +
                ", yearOfCreation=" + yearOfCreation +
                ", price=" + price +
                '}';
    }
}
