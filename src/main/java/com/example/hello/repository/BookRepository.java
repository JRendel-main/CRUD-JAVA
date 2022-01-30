package com.example.hello.repository;

import com.example.hello.model.BookModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class BookRepository implements CRUDrepository<BookModel> {



    @Override
    public List<BookModel> list() {
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

    @Override
    public BookModel create(BookModel model) {
        String query = "insert into book values (" + model.getId() + ", '"
                + model.getTitle() + "', '"
                + model.getAuthor() + "', "
                + model.getYear() + ", "
                + model.getPages() + ")";
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
        String query = "update book set title = '" + model.getTitle()
                +"' , author =  '" + model.getAuthor()
                + "' , year = " + model.getYear()
                + ", pages = " + model.getPages()
                + " where id = " + id + "";
        executeQuery(query);
        //TODO get updated model
        return model;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from book where id = " + id + "";
        executeQuery(query);
        //TODO return true if deletion is completed
        return true;
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
