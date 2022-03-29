module com.example.animals {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.animals to javafx.fxml;
    exports com.example.animals;
}