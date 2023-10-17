package com.example.lab4fx.model;

import com.example.lab4fx.FileService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

//private List<Product> products = new ArrayList<>(Arrays.asList( //на случай удаления файла
//        new Product("Семена цветов", "10.0"),
//        new Product("Удобрение для растений", "15.0"),
//        new Product("Садовые инструменты", "25.0")
//        ));

public class ProductList implements Serializable, Observable {
    private List<Product> products;
    private List<Observer> observers;
    public ProductList(){
        products = FileService.readFromFile(products, "products.txt");

        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer observer : observers) {
            observer.notification(message);
        }
    }

    public void addProduct(String name, String price) {
        products.add(new Product(name, price));
        FileService.writeInFile(products, "products.txt");
        notifyObservers("Product added");
    }
    //implement remove by id ??
    public void removeProduct(String name, String price) {
        products = products.stream()
                .filter(product ->
                        !product.getPrice().equals(price)
                        && !product.getName().equals(name))
                .collect(Collectors.toList());
        FileService.writeInFile(products, "products.txt");

        notifyObservers("Product deleted");
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
