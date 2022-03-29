package com.example.animals;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Login extends Application implements Initializable {



        @FXML
        private PasswordField txt_password;

        @FXML
        private TextField txt_User;

        @FXML
        private Button btn_login;
        @FXML
        private Button btn_cancel;

        @FXML
        private ImageView banding_img;
        @FXML
        private Label lblMessege;
        @FXML
        private ImageView neUserIMG;

    public void login(ActionEvent event){
        validateLogin();
         if (txt_User.getText().isBlank()==false && txt_password.getText().isBlank()==false){
            lblMessege.setText("you try to login");

        }else {
            lblMessege.setText("please enter username and password");
        }}
    public void cancel(ActionEvent event){
        Stage stage=(Stage) btn_cancel.getScene().getWindow();
        stage.close();

    }
    public void validateLogin(){
        DataBaseConnection connection=new DataBaseConnection();
        Connection connectDB= connection.getConnection();
        String verifyLogin ="SELECT count(1) from user_account where username='rosha' " + txt_User.getText()+  "AND  password ='123456' "+ txt_password.getText();

    try{
        PreparedStatement pr=connectDB.prepareStatement(verifyLogin);
        ResultSet queryResult=pr.executeQuery(verifyLogin);
        while(queryResult.next()){
            if(queryResult.getInt(1)==1){
                lblMessege.setText("Congratulations !");
            }else {
                lblMessege.setText("invalid Login");
            }
        }
    }catch (Exception e){
        e.printStackTrace();
        e.getMessage();
}}
    @Override
    public void start(Stage primaryStage) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile =new File("C:\\Users\\Meme\\IdeaProjects\\animals\\src\\main\\resources\\AnimalHome.png");
        Image barandingImage=new Image(brandingFile.toURI().toString());
        banding_img.setImage(barandingImage);

        File newUserFile =new File("C:\\Users\\Meme\\IdeaProjects\\animals\\src\\main\\resources\\new user - Copy.png");
        Image newUser=new Image(newUserFile.toURI().toString());
        neUserIMG.setImage(newUser);
    }
}
