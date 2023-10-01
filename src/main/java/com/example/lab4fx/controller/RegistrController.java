package com.example.lab4fx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrController {

    @FXML
    private TextField regPasswordTF = new TextField();
    @FXML
    private TextField regLoginTF = new TextField();

    @FXML //типо querySelector при работе с DOM
    private Button registerButton;

    public void initialize() {
        registerButtonHandler();
    }

    public void registerButtonHandler() {
        registerButton.setOnAction(e -> {
            String password = regPasswordTF.getText();
            String login = regLoginTF.getText();
            if(password.equals("admin") && login.equals("admin")){
                openProductListWindow();
            }
        });
    }
    public void openProductListWindow() {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4fx/main.fxml"));
            Parent root = loader.load();
//            Stage stage = (Stage)((Node)event.getSource().getScene().getWindow());
            Stage stage = (Stage)(registerButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
