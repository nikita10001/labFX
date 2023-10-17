package com.example.lab4fx.model;

import javafx.beans.Observable;

import java.io.Serializable;
import java.util.Observer;

public class Product implements Serializable {
//    private int id;
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    public Double getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

}
