module com.example.lab4fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab4fx to javafx.fxml;
    exports com.example.lab4fx;
    exports com.example.lab4fx.controller;
    opens com.example.lab4fx.controller to javafx.fxml;
}