package com.example.lab4fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class WindowSwitcher {
    public static void switchWindow(ActionEvent event, URL url) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            //если без, то открывается миллиард окон
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();  //////////
//            Stage stage = (Stage)(loginButton.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String getUrl(String window) {
        return "/com/example/lab4fx/"+window+".fxml";
    }
}
