package com.example.hello;

import com.example.hello.model.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;


import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField textField_Id;

    @FXML
    private TableColumn<BookModel, Integer> tableColumn_id;

    @FXML
    private TableColumn<BookModel, String> tableColumn_title;

    @FXML
    private TableColumn<BookModel, String> tableColumn_author;

    @FXML
    private TableColumn<BookModel, Integer> tableColumn_year;

    @FXML
    private TableColumn<BookModel, Integer> tableColumn_pages;

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

    public ObservableList<BookModel> list() {
        ObservableList<BookModel> list = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM books";
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            BookModel model;
            while (resultSet.next()) {
                model = new BookModel(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getInt("year"),
                        resultSet.getInt("pages"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void showBooks() {
        ObservableList<BookModel> list = list();
        tableColumn_id.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("id"));
        tableColumn_title.setCellValueFactory(new PropertyValueFactory<BookModel, String>("title"));
        tableColumn_author.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
        tableColumn_year.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("year"));
        tableColumn_pages.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("pages"));
    }
}