package com.example.lab4fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
//    private Stage stage;
//    private Scene scene;
//    private Parent root;


    @FXML
    TextField passwordLabel = new TextField();
    @FXML
    TextField loginLabel = new TextField();

    public void loginBtnHandler(ActionEvent event){
        String password = passwordLabel.getText();
        String login = loginLabel.getText();
        if(password.equals("admin") && login.equals("admin")){
            switchToMain(event);
        }else{
            System.out.println("Неправильный пароль");
        }
    }

    public void switchToMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lab4fx/main.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
