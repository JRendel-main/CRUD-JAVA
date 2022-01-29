module com.example.hello {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.hello to javafx.fxml;
    opens com.example.hello.model to javafx.base;
    exports com.example.hello;
}