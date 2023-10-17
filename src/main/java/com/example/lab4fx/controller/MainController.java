package com.example.lab4fx.controller;

import com.example.lab4fx.model.Observer;
import com.example.lab4fx.model.Product;
import com.example.lab4fx.model.ProductList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Observer {

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

    @Override
    public void notification(String message) {
        if(message.equals("Product added")) {
            productTableView.setItems(productList.getProductList());
        }
        if(message.equals("Product deleted")) {
            productTableView.setItems(productList.getProductList());
        }
    }

    @FXML
    private void initialize() {
        // Загрузка списка товаров и отображение данных в элементах интерфейса
        loadProductList();
        setupTableColumns();
        //обработчики событий
        logoutBtnHandler();
        addGoodButtonHandler();
        searchProductButtonHandler();
        deleteProductHanlder();

        //регистрация слушателя
        productList.registerObserver(this);
    }

    private void deleteProductHanlder() {
        productTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Проверка на двойной клик
                Product selectedItem = productTableView.getSelectionModel().getSelectedItem(); // Получение выбранного элемента
                productList.removeProduct(selectedItem.getName(), selectedItem.getPrice());
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
            if(!productName.getText().isEmpty() && !productPrice.getText().isEmpty()) {
                productList.addProduct(productName.getText(), productPrice.getText());
                productName.setText("");
                productPrice.setText("");
            }
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
