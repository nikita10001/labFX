package com.example.lab4fx.controller;

import com.example.lab4fx.model.Product;
import com.example.lab4fx.model.ProductList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField queryProduct;
    @FXML
    private Button searchProductButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button addProductButton;
    @FXML
    private TableView<Product> productTableView;

    ProductList productList = new ProductList();


    @FXML
    private void initialize() {
        // Загрузка списка товаров и отображение данных в элементах интерфейса
        loadProductList();
        setupTableColumns();
        logoutBtnHandler();
        addGoodButtonHandler();
        searchProductButtonHandler();
        ///++==========================================================
        //КНОПКА ВЫЙТИ НЕ РАБОТАЕТ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        ///++==========================================================
        
        productTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Проверка на одиночный клик
                Product selectedItem = productTableView.getSelectionModel().getSelectedItem(); // Получение выбранного элемента
                productTableView.getItems().remove(selectedItem); // Удаление выбранного элемента из таблицы
            }
        });
    }

    private void logoutBtnHandler() {
        logoutButton.setOnAction(e -> {
            WindowSwitcher.switchWindow(e,
                    getClass().getResource(WindowSwitcher.getUrl("login")));
        });
    }

    public void addGoodButtonHandler() {
        addProductButton.setOnAction(e -> {
            if(!productName.getText().isEmpty() && !productPrice.getText().isEmpty()){
                productList.addProduct(productName.getText(), productPrice.getText());
                productName.setText("");
                productPrice.setText("");
            }
            productTableView.setItems(productList.getProductList());

        });
    }
    public void searchProductButtonHandler() {
        searchProductButton.setOnAction(e -> {
            String query = queryProduct.getText().toLowerCase();
            ObservableList<Product> filteredList = FXCollections.observableArrayList();
            for (Product product : productList.getProductList()) {
                if (product.getName().toLowerCase().contains(query)) {
                    filteredList.add(product);
                }
            }
            productTableView.setItems(filteredList);
        });
    }
    private void setupTableColumns() {
        TableColumn<Product, String> nameColumn =
                (TableColumn<Product, String>) productTableView.getColumns().get(0);
        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));

        TableColumn<Product, Double> priceColumn =
                (TableColumn<Product, Double>) productTableView.getColumns().get(1);
        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
    }
    private void loadProductList() {
        productTableView.setEditable(true);
        productTableView.setItems(productList.getProductList());
    }
}
