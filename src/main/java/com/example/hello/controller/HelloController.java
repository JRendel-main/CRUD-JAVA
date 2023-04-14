package com.example.hello.controller;

import com.example.hello.model.BookModel;
import com.example.hello.repository.BookRepository;
import com.example.hello.service.BookService;
import com.example.hello.service.CRUDService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;


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
    private ComboBox<String> comboBox_genre;

    @FXML
    private ComboBox<String> comboBox_status;

    @FXML
    private ComboBox<String> comboBox_state;

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
    private TableColumn<BookModel, String> tableColumn_genre;

    @FXML
    private TableColumn<BookModel, String> tableColumn_status;

    @FXML
    private TableColumn<BookModel, String> tableColumn_state;
    @FXML
    private TableView<BookModel> tableView;
    @FXML
    private Button button_insert;
    @FXML
    private Button button_update;
    @FXML
    private Button button_delete;

    CRUDService<BookModel> service;

    public HelloController() {
        BookRepository repository = new BookRepository();
        service = new BookService(repository);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
        ObservableList<String> genreList = FXCollections.observableArrayList(
                "Action", "Adventure", "Comedy", "Drama", "Fantasy", "Sci-Fi", "Thriller");
        comboBox_genre.setItems(genreList);

        ObservableList<String> statusList = FXCollections.observableArrayList(
                "Available", "Borrowed", "Lost");
        comboBox_status.setItems(statusList);

        ObservableList<String> stateList = FXCollections.observableArrayList(
                "New", "Damaged", "Missing", "Slightly-Damaged", "Used");
        comboBox_state.setItems(stateList);
    }

    @FXML
    private void tableViewOnMouseClicked(MouseEvent event) {
        BookModel item = tableView.getSelectionModel().getSelectedItem();
        if (item != null) {
            textField_id.setText(String.valueOf(item.getId()));
            textField_title.setText(item.getTitle());
            textField_author.setText(item.getAuthor());
            textField_year.setText(String.valueOf(item.getYear()));
            textField_pages.setText(String.valueOf(item.getPages()));
            comboBox_genre.setValue(item.getGenre());
            comboBox_status.setValue(item.getStatus());
            comboBox_state.setValue(item.getState());
    }
    }
    @FXML
    private void button_insertOnAction(Event event) {
        BookModel model = new BookModel(
                Integer.parseInt(textField_id.getText()),
                textField_title.getText(),
                textField_author.getText(),
                Integer.parseInt(textField_year.getText()),
                Integer.parseInt(textField_pages.getText()),
                comboBox_genre.getValue(),
                comboBox_status.getValue(),
                comboBox_state.getValue()
        );
        service.create(model);
        showBooks();
    }
    @FXML
    private void button_updateOnAction(Event event) {
        int id = Integer.parseInt(textField_id.getText());
        BookModel model = new BookModel(
                id,
                textField_title.getText(),
                textField_author.getText(),
                Integer.parseInt(textField_year.getText()),
                Integer.parseInt(textField_pages.getText()),
                comboBox_genre.getValue(),
                comboBox_status.getValue(),
                comboBox_state.getValue()
        );
        service.update(id, model);
        showBooks();
    }
    @FXML
    private void button_deleteOnAction(Event event) {
        int id = Integer.parseInt(textField_id.getText());
        service.delete(id);
        showBooks();
    }

    private void showBooks() {
        ObservableList<BookModel> list = (ObservableList<BookModel>) service.list();
        tableColumn_id.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("id"));
        tableColumn_title.setCellValueFactory(new PropertyValueFactory<BookModel, String>("title"));
        tableColumn_author.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
        tableColumn_year.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("year"));
        tableColumn_pages.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("pages"));
        tableColumn_genre.setCellValueFactory(new PropertyValueFactory<BookModel, String>("genre"));
        tableColumn_status.setCellValueFactory(new PropertyValueFactory<BookModel, String>("status"));
        tableColumn_state.setCellValueFactory(new PropertyValueFactory<BookModel, String>("state"));

        tableView.setItems(list);
    }

    public void button_clearOnAction(ActionEvent actionEvent) {
        textField_id.setText("");
        textField_title.setText("");
        textField_author.setText("");
        textField_year.setText("");
        textField_pages.setText("");
        comboBox_genre.setValue("");
        comboBox_status.setValue("");
        comboBox_state.setValue("");
    }

    public void ShowAvailableBooksOnAction(ActionEvent actionEvent) {
        // get the list of books from showAvail method in BookService class and display it in the table
        ObservableList<BookModel> list = (ObservableList<BookModel>) service.showAvail();
        tableColumn_id.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("id"));
        tableColumn_title.setCellValueFactory(new PropertyValueFactory<BookModel, String>("title"));
        tableColumn_author.setCellValueFactory(new PropertyValueFactory<BookModel, String>("author"));
        tableColumn_year.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("year"));
        tableColumn_pages.setCellValueFactory(new PropertyValueFactory<BookModel, Integer>("pages"));
        tableColumn_genre.setCellValueFactory(new PropertyValueFactory<BookModel, String>("genre"));
        tableColumn_status.setCellValueFactory(new PropertyValueFactory<BookModel, String>("status"));
        tableColumn_state.setCellValueFactory(new PropertyValueFactory<BookModel, String>("state"));
    }
}