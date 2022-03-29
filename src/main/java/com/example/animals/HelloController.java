package com.example.animals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


        @FXML
        private Button btn_Add;
        @FXML
        private Button btn_Delete;
        @FXML
        private Button btn_update;
        @FXML
        private Button btn_close;


        @FXML
        private TableColumn<Booking, Integer> col_ID;
        @FXML
        private TableColumn<Booking, Integer> col_week;
        @FXML
        private TableColumn<Booking, Integer> col_cust;
        @FXML
        private TableColumn<Booking, Integer> col_cage;


        @FXML
        private Label lbl_AnimalsHome;

        @FXML
        private TableView<Booking> BKtblview;


        @FXML
        private TextField txt_cust_no;
        @FXML
        private TextField txt_cage_no;
        @FXML
        private TextField txt_week_no;
        @FXML
        private TextField txt_id;






        public Connection dataBaseLink;

        public Connection getConnection(){
                String dataBaseName=" ";
                String dataBaseUser="sa";
                String dataBasePassword="1234";
                String url="jdbc:sqlserver://localhost:1433;databaseName=animals";
                try{
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        dataBaseLink= DriverManager.getConnection(url,dataBaseUser,dataBasePassword);
                }catch (Exception e){
                        e.printStackTrace();
                        e.getMessage();
                }
                return dataBaseLink;
        }
        public void EXECUTE_insert_booking (int cage_no,int week_no,int cust_no) throws SQLException {
                getConnection();
                String sql=("EXCUTE insert_booking ?,?,?");
                PreparedStatement ps = dataBaseLink.prepareStatement(sql);
                ResultSet rs= ps.executeQuery();


        }
        public void EXECUTE_insert_pet (int pet_no) throws SQLException {
                getConnection();
                String sql=("EXCUTE insert_pet ?");
                PreparedStatement ps = dataBaseLink.prepareStatement(sql);
                ResultSet rs= ps.executeQuery();


        }
        public void EXECUTE_insert_customer (int cust_no,String cust_name,String cust_address,String phone) throws SQLException {
                getConnection();
                String sql=("EXCUTE insert_customer ?,?,?,?");
                PreparedStatement ps = dataBaseLink.prepareStatement(sql);
                ResultSet rs= ps.executeQuery();


        }

        @FXML
        void btnAddAction(ActionEvent event) {
                Booking bk=getBook();
                if(bk!=null){
                        BKtblview.getItems().add(bk);
                }else
                        BKtblview.getItems().addAll();

        }
        //method to show the content of text fields
        private Booking getBook(){
                Booking book=null;

                try{
                        int  id= Integer.parseInt(txt_id.getText());
                        int  numOfCustomer= Integer.parseInt(txt_cust_no.getText());
                        int numOfWeek= Integer.parseInt(txt_week_no.getText());
                        int numOfCage= Integer.parseInt(txt_cage_no.getText());
                        book=new Booking(id,numOfCustomer,numOfCage,numOfWeek);
                }catch(NumberFormatException ne){
                        ne.getMessage();
                }

                return book;
        }


        @FXML
        void btnDeleteAction(ActionEvent event) {
                Booking selected=BKtblview.getSelectionModel().getSelectedItem();
                BKtblview.getItems().removeAll(selected);
        }
        @FXML
        void btnCloseAction(ActionEvent event){
                System.exit(0);

        }
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                col_ID.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("ID"));
                col_cust.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("customer_no"));
                col_cage.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("cage_no"));
                col_week.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("week_no"));
                BKtblview.getItems().addAll();
        }
}