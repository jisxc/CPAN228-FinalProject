package com.example.dcmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class DcItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Brand brand;
    @Min(2022)
    private int yearOfCreation;
    @Min(0)
    private double price;
    @Min(1)
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "centre_id")
    private DistributionCentre distributionCentre;

    public DcItem() {}
    public DcItem(String name, Brand brand, int yearOfCreation, double price, int quantity, DistributionCentre distributionCentre) {
        this.name = name;
        this.brand = brand;
        this.yearOfCreation = yearOfCreation;
        this.price = price;
        this.quantity = quantity;
        this.distributionCentre = distributionCentre;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }
    public int getYearOfCreation() { return yearOfCreation; }
    public void setYearOfCreation(int yearOfCreation) { this.yearOfCreation = yearOfCreation; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public DistributionCentre getDistributionCentre() { return distributionCentre; }
    public void setDistributionCentre(DistributionCentre distributionCentre) { this.distributionCentre = distributionCentre; }
}
