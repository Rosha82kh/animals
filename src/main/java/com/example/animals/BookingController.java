package com.example.animals;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.ResourceBundle;

public class BookingController  implements Initializable {

        @FXML
        private DatePicker calender;

        @FXML
        private Label lbl_msg;


        @FXML
        private Button btn_book;


        @FXML
        private Button cage_6;
        @FXML
        private Button cage_5;

        @FXML
        private Button cage_4;

        @FXML
        private Button cage_3;

        @FXML
        private Button cage_2;

        @FXML
        private Button cage_1;

        @FXML
        private TextField txt_phone;
        @FXML
        private TextField txt_price;

        @FXML
        private TextArea txtArea;

        @FXML
        private ImageView imgView;


        public void isCustomer(){
            getConnection();
            try{
                PreparedStatement ps = dataBaseLink.prepareStatement("  SELECT phone from customer where (phone==?)");
                ps.setString(1, String.valueOf(txt_phone));
                  ResultSet rs=ps.executeQuery();
                if(rs.next()==(true)){
                lbl_msg.setText("The phone number already exists");
            }else {
                lbl_msg.setText("Please register first");
            }
            }catch (SQLException e){
                e.getMessage();
            }
            }


        public void getInvoice(){
            String customer=txt_phone.getText();
            int cages=cageNo;
            String price=txt_price.getText();
            LocalDate bookDate=calender.getValue();
            String bookFormattedDate=bookDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
           // SimpleDateFormat date_form=new SimpleDateFormat("YYYY-MM-DD");
             txtArea.setText(bookFormattedDate);
            txtArea.setWrapText(true);
            txtArea.setEditable(false);
            txtArea.setText(txtArea.getText()+"********************************************");
            txtArea.setText(txtArea.getText()+"********************** Bill *******************");
            txtArea.setText(txtArea.getText()+" Customer"+"\t"+customer+"\n");
            txtArea.setText(txtArea.getText()+" Cage No"+"\t"+cages+"\n");
            txtArea.setText(txtArea.getText()+" Price"+"\t"+price+"\n");
            txtArea.setText(txtArea.getText()+" Date"+"\t"+bookFormattedDate+"\n");
            txtArea.setText(txtArea.getText()+"******************** Thank you *******************");

        }


        public void calculatePrice(){
          double   price=30*cageNo;
            txt_price.setText(String.valueOf(price));
         }



        public void getDate(ActionEvent event){
            LocalDate bookDate=calender.getValue();
            Locale locale = Locale.US;
            int weekOfYear = bookDate.get(WeekFields.of(locale).weekOfWeekBasedYear());
            String bookFormattedDate=bookDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"));
            /* DatePicker startDatePicker = new DatePicker();
            DatePicker endDatePicker = new DatePicker();
            startDatePicker.setValue(LocalDate.now());
           endDatePicker.setValue(startDatePicker.getValue().plusDays(7));

            LocalDate date = startDatePicker.getValue(); // input from your date picker
            Locale locale = Locale.US;
            int weekOfYear = date.get(WeekFields.of(locale).weekOfWeekBasedYear());*/
         }


         public void makeBooking(ActionEvent event){

             event.getSource().toString();
             isCustomer();
             getDate(event);
             getCage(event);
             calculatePrice();
             getInvoice();
             lbl_msg.setText("Booking has been done");
         }



        int cageNo=0;
        public void  getCage(ActionEvent event){
            if(event.getSource()==cage_1){
                cageNo=1;
            }else if(event.getSource()==cage_2){
                cageNo=2;
            }else if(event.getSource()==cage_3){
                cageNo=3;
            }else if(event.getSource()==cage_4){
                cageNo=4;
            }else if(event.getSource()==cage_5){
                cageNo=5;
            }else if(event.getSource()==cage_6){
                cageNo=6;
            }
            }



        public Connection dataBaseLink;
        public Connection getConnection() {
        String dataBaseName="animals";
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



        public void checkAvailability(){
        getConnection();
           // getDate();
        DatePicker startDatePicker = new DatePicker();
        LocalDate date = startDatePicker.getValue(); // input from your date picker
        Locale locale = Locale.US;
        int weekOfYear = date.get(WeekFields.of(locale).weekOfWeekBasedYear());
        try{
             PreparedStatement ps = dataBaseLink.prepareStatement("SELECT * from booking where week_no = ? and cust_no=? and cage_no=?");
            ps.setInt(1,1);
            ps.setInt(2,12);
            ps.setInt(3,55446);
            ResultSet rs=ps.executeQuery();
            if(rs.next()==true){
                lbl_msg.setText("This cage No already booked");
             }else {
                ps=dataBaseLink.prepareStatement("insert into booking (cage_no,week_no,cust_no) values (?,?,?)");
                ps.setInt(1,cageNo);
                ps.setInt(2,weekOfYear );
                ps.setString(3, String.valueOf(txt_phone));
                int k=ps.executeUpdate();
                if(k==1){
                    lbl_msg.setText("cage booked");
                }else {
                    lbl_msg.setText("wrong entries");
                }
            }
        }catch (SQLException | RuntimeException e){
            e.getMessage();
        }

        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
         }
        }
