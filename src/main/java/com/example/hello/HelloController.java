package com.example.hello;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
}