package com.example.hello;

import com.example.hello.model.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;


import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TextField textField_id;

    @FXML
    private TextField textField_title;

    @FXML
    private TextField textField_author;

    @FXML
    private TextField textField_year;

    @FXML
    private TextField textField_pages;

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
    private TableView<BookModel> tableView;

    @FXML
    private Button button_insert;

    @FXML
    private Button button_update;

    @FXML
    private Button button_delete;

    @FXML
    private void onInsertButtonClick(Event event) {
        if(event.getSource() == button_insert) {
            onInsertButtonClick();
        } else if (event.getSource() == button_update) {
            onUpdateButtonClick();
        } else if (event.getSource() == button_delete) {
            onDeleteButtonClick();
        }
    }

    @FXML
    private void onTableViewMouseClicked(MouseEvent event) {
        BookModel item = tableView.getSelectionModel().getSelectedItem();
        if (item != null) {
            textField_id.setText(String.valueOf(item.getId()));
            textField_title.setText(item.getTitle());
            textField_author.setText(item.getAuthor());
            textField_year.setText(String.valueOf(item.getYear()));
            textField_pages.setText(String.valueOf(item.getPages()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
    }

    public Connection getConnection() {
        Connection connection;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db?serverTimezone=UTC", "root", "ejek");
                return connection;
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                return null;
            }
    }

    public ObservableList<BookModel> list() {
        ObservableList<BookModel> list = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM book";
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
                list.add(model);
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

        tableView.setItems(list);
    }

    private void onInsertButtonClick() {
        String query = "insert into book values (" + textField_id.getText() + ", '"
                + textField_title.getText() + "', '"
                + textField_author.getText() + "', "
                + textField_year.getText() + ", "
                + textField_pages.getText() + ")";
        executeQuery(query);
        showBooks();
    }

    private void onUpdateButtonClick() {
        String query = "update book set title = '" + textField_title.getText()
                +"' , author =  '" + textField_author.getText()
                + "' , year = " + textField_year.getText()
                + ", pages = " + textField_pages.getText()
                + " where id = " + textField_id.getText() + "";
        executeQuery(query);
        showBooks();
    }

    private void onDeleteButtonClick() {
        String query = "delete from book where id = " + textField_id.getText() + "";
        executeQuery(query);
        showBooks();
    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}