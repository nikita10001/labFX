package com.example.lab4fx.model;

import com.example.lab4fx.FileService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//private List<Product> products = new ArrayList<>(Arrays.asList( //на случай удаления файла
//        new Product("Семена цветов", "10.0"),
//        new Product("Удобрение для растений", "15.0"),
//        new Product("Садовые инструменты", "25.0")
//        ));

public class ProductList implements Serializable {
    private List<Product> products;
    public ProductList(){
        products = FileService.readFromFile(products, "products.txt");
    }

    public void addProduct(String name, String price) {
        products.add(new Product(name, price));
        FileService.writeInFile(products, "products.txt");
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProductList(ObservableList<Product> products) {
        this.products = products;
    }
    public ObservableList<Product> getProductList() {
        return FXCollections.observableList(products);
    }
}
