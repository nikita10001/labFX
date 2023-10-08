//package com.example.lab4fx.view;
//
//import com.example.lab4fx.model.Product;
//import com.example.lab4fx.model.ProductList;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//public class ProductView {
//    @FXML
//    private TableView<Product> productTableView;
//
//
//
//    public void setupTableColumns() {
//        TableColumn<Product, String> nameColumn =
//                (TableColumn<Product, String>) productTableView.getColumns().get(0);
//        nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
//        TableColumn<Product, Double> priceColumn =
//                (TableColumn<Product, Double>) productTableView.getColumns().get(1);
//        priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
//    }
//
//
//   public void loadProductList(ProductList productList) {
//        productTableView.setEditable(true);
//        productTableView.setItems(productList.getProductList());
//    }
//}
