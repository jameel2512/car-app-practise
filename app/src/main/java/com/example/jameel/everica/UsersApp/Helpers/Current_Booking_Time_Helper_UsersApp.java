package com.example.jameel.everica.UsersApp.Helpers;

/**
 * Created by jameel on 5/19/2020.
 */

public class Current_Booking_Time_Helper_UsersApp {

    String start_date,End_Date,start_Time,End_Time,Car_Owner_ID,Customer_ID,Start_Button_Clicked;

   // boolean Start_Button_Clicked;


    public Current_Booking_Time_Helper_UsersApp()
    {

    }

    public Current_Booking_Time_Helper_UsersApp(String start_date, String end_Date, String start_Time,
                                                String end_Time, String car_Owner_ID, String customer_ID,
                                                String start_Button_Clicked) {
        this.start_date = start_date;
        End_Date = end_Date;
        this.start_Time = start_Time;
        End_Time = end_Time;
        Car_Owner_ID = car_Owner_ID;
        this.Customer_ID=customer_ID;
        this.Start_Button_Clicked=start_Button_Clicked;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getStart_Button_Clicked() {
        return Start_Button_Clicked;
    }

    public void setStart_Button_Clicked(String start_Button_Clicked) {
        Start_Button_Clicked = start_Button_Clicked;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }

    public String getStart_Time() {
        return start_Time;
    }

    public void setStart_Time(String start_Time) {
        this.start_Time = start_Time;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String end_Time) {
        End_Time = end_Time;
    }

    public String getCar_Owner_ID() {
        return Car_Owner_ID;
    }

    public void setCar_Owner_ID(String car_Owner_ID) {
        Car_Owner_ID = car_Owner_ID;
    }
}
