package com.example.hello;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;


import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField textField_Id;

    @FXML
    protected void onInsertButtonClick() {
        textField_Id.setText("elo");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Connection getConnection() {
        Connection connection;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "ejek");
                return connection;
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                return null;
            }
    }
}