package com.example.lab4fx.controller;

import com.example.lab4fx.model.UserList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrController {

    @FXML
    private TextField regPasswordTF = new TextField();
    @FXML
    private TextField regLoginTF = new TextField();
    @FXML
    private TextField regNameTF = new TextField();

    @FXML //типо querySelector при работе с DOM
    private Button registerButton;

    @FXML
    private Text regError;

    UserList userList = new UserList();

    public void initialize() {
        registerButtonHandler();
    }

    public void registerButtonHandler() {
        registerButton.setOnAction(event -> {
            String name = regNameTF.getText();
            String password = regPasswordTF.getText();
            String login = regLoginTF.getText();



            if(userList.isExistUser(password, login)) {
                regError.setVisible(true);
            }
//            if(password.length() < 8) {
//                regError.setText("Пароль не может быть меньше 8 символов!!!");
//                regError.setVisible(true);
            else{
                userList.addUser(name, password, login);
                WindowSwitcher.switchWindow(event,
                        getClass().getResource(WindowSwitcher.getUrl("main")));
            }
        });
    }

}
