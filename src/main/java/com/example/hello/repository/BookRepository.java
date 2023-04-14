package com.example.hello.repository;

import com.example.hello.model.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.List;

public class BookRepository implements CRUDrepository<BookModel> {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "";

    @Override
    public List<BookModel> list() {
        ObservableList<BookModel> list = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "select * from books";
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
                        resultSet.getInt("pages"),
                        resultSet.getString("genre"),
                        resultSet.getString("status"),
                        resultSet.getString("state")
                        );
                list.add(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public BookModel create(BookModel model) {
        String query = "insert into books values (" + model.getId() + ", '"
                + model.getTitle() + "', '"
                + model.getAuthor() + "', "
                + model.getYear() + ", "
                + model.getPages() + ", '"
                + model.getGenre() + "', '"
                + model.getStatus() + "', '"
                + model.getState() + "')";
        executeQuery(query);
        //TODO get created model
        return model;
    }

    @Override
    public BookModel read(int id) {
        return null;
    }

    @Override
    public BookModel update(int id, BookModel model) {
        String query = "update books set title = '" + model.getTitle() + "', "
                + "author = '" + model.getAuthor() + "', "
                + "year = " + model.getYear() + ", "
                + "pages = " + model.getPages() + ", "
                + "genre = '" + model.getGenre() + "', "
                + "status = '" + model.getStatus() + "', "
                + "state = '" + model.getState() + "' "
                + "where id = " + id + "";
        executeQuery(query);
        //TODO get updated model
        return model;
    }

    @Override
    public BookModel showAvail() {
        return null;
    }

    @Override
    public BookModel showAvail(int id, BookModel model) {
        String query = "update books set title = '" + model.getTitle() + "', "
                + "author = '" + model.getAuthor() + "', "
                + "year = " + model.getYear() + ", "
                + "pages = " + model.getPages() + ", "
                + "genre = '" + model.getGenre() + "', "
                + "status = '" + model.getStatus() + "', "
                + "state = '" + model.getState() + "' "
                + "where id = " + "Available" + "";
        executeQuery(query);
        //TODO get updated model
        return model;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from books where id = " + id + "";
        executeQuery(query);
        //TODO return true if deletion is completed
        return true;
    }

    public Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            return connection;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

    private void executeQuery(String query) {
        Connection connection = getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Error");
            alert.setHeaderText("An error occurred while executing SQL query.");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
