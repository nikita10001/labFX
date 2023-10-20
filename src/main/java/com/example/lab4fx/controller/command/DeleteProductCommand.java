package com.example.lab4fx.controller.command;

import com.example.lab4fx.model.ProductList;

public class DeleteProductCommand implements Command {
    private ProductList productList;
    private String productName;
    private double productPrice;

    public DeleteProductCommand(ProductList productList, String productName, double productPrice) {
        this.productList = productList;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    @Override
    public void execute() {
        productList.removeProduct(productName, productPrice);
    }
}

