package com.example.crud.model.product;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank
    @Column
    private String name;

    @NotBlank
    @Pattern(regexp = "^[0-9]{13}$", message = "Format error")
    @Column(unique = true)
    private String barCode;

    @Min(1)
    @Column
    private double price;

    //Constructors
    public Product(){

    }

    public Product(String name, String barCode, double price){
        this();
        this.setName(name);
        this.setBarCode(barCode);
        this.setPrice(price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", barCode='" + barCode + '\'' +
                ", price=" + price +
                '}';
    }

    //Getters and Setttes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
