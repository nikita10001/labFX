package com.example.lab4fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField passwordLabel = new TextField();
    @FXML
    private TextField loginLabel = new TextField();
    @FXML //типо querySelector при работе с DOM
    private Button loginButton;
    @FXML
    private Button toRegisterBtn;

    public void initialize(){
        loginButtonHandler();
        toRegisterBtnHandler();
    }
    public void toRegisterBtnHandler() {
        toRegisterBtn.setOnAction(actionEvent -> {
            openRegisterWindow();
        });
    }

    public void loginButtonHandler() {
        loginButton.setOnAction(event -> {
            String password = passwordLabel.getText();
            String login = loginLabel.getText();
            if(password.equals("admin") && login.equals("admin")){
                openProductListWindow();
            }else{
                System.out.println("Неправильный пароль");
            }
        });
    }

    public void openRegisterWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4fx/register.fxml"));
            Parent root = loader.load();
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();  //////////
            Stage stage = (Stage)(toRegisterBtn.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openProductListWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4fx/main.fxml"));
            Parent root = loader.load();
//            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();  //////////
            Stage stage = (Stage)(loginButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
