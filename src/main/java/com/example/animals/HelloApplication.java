package com.example.animals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try{
       FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("animals.fxml"));
       // FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
       // FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("Booking.fxml"));


        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }}

    public static void main(String[] args) {
        launch();
    }
}