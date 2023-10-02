package com.example.lab4fx.controller;

import com.example.lab4fx.model.UserList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private TextField passwordTF = new TextField();
    @FXML
    private TextField loginTF = new TextField();
    @FXML //типо querySelector при работе с DOM
    private Button loginButton;
    @FXML
    private Button toRegisterBtn;
    @FXML
    private Text errorLabel;

    UserList userList = new UserList();

    public void initialize(){
        loginButtonHandler();
        toRegisterBtnHandler();
    }
    public void toRegisterBtnHandler() {
        toRegisterBtn.setOnAction(event -> {
            WindowSwitcher.switchWindow(event,
                getClass().getResource(WindowSwitcher.getUrl("register")));
        });
    }

    public void loginButtonHandler() {
        loginButton.setOnAction(event -> {
            if(userList.isExistUser(loginTF.getText(), passwordTF.getText())){
                WindowSwitcher.switchWindow(event,
                    getClass().getResource(WindowSwitcher.getUrl("main")));
            }else{
                errorLabel.setVisible(true);
            }
        });
    }





}
