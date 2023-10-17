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
    public void notifyObservers(Notification notification) {
        for(Observer observer : observers) {
            observer.notification(notification);
        }
    }

    public void addProduct(String name, Double price) {
        products.add(new Product(name, price));
        FileService.writeInFile(products, "products.txt");
        notifyObservers(Notification.ADDED);
    }
    //implement remove by id ??
    public void removeProduct(String name, Double price) {
        products = products.stream()
                .filter(product ->
                        !Objects.equals(product.getPrice(), price)
                        && !product.getName().equals(name))
                .collect(Collectors.toList());
        FileService.writeInFile(products, "products.txt");

        notifyObservers(Notification.DELETED);
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
