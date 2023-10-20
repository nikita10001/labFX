package com.example.lab4fx.controller;

import com.example.lab4fx.controller.command.AddProductCommand;
import com.example.lab4fx.controller.command.Command;
import com.example.lab4fx.controller.command.DeleteProductCommand;
import com.example.lab4fx.model.Notification;
import com.example.lab4fx.model.Observer;
import com.example.lab4fx.model.Product;
import com.example.lab4fx.model.ProductList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Observer {

    @FXML
    private TextField productName;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField queryProduct;
    @FXML
    private Label notifyLabel;
    @FXML
    private Label numError;
    @FXML
    private Button searchProductButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button addProductButton;
    @FXML
    private TableView<Product> productTableView;

    ProductList productList = new ProductList();

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    private void executeCommand() {
        if (command != null) {
            command.execute();
        }
    }
    //передавать товары
    @Override
    public void notification(Notification notification) {
        if(notification == Notification.ADDED){
            notifyLabel.setText("Товар добавлен!");
            numError.setVisible(false);
        }else if(notification == Notification.DELETED){
            notifyLabel.setText("Товар удалён!");
        }
        productTableView.setItems(productList.getProductList());
        notifyLabel.setVisible(true);
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
                command = new DeleteProductCommand(productList, selectedItem.getName(), selectedItem.getPrice());
                executeCommand();
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
                try{
                    double price = Double.parseDouble(productPrice.getText());
                    command = new AddProductCommand(productList, productName.getText(), price);
                    executeCommand();
                    productName.setText("");
                    productPrice.setText("");
                }
                catch (Exception exception){
                    numError.setVisible(true);
                    numError.setText("Цена не может быть строкой");
                    productPrice.setText("");
                }
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
