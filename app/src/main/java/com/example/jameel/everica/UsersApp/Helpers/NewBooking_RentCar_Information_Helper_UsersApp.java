package com.example.jameel.everica.UsersApp.Helpers;

/**
 * Created by jameel on 5/8/2020.
 */

public class NewBooking_RentCar_Information_Helper_UsersApp {

    String car_owner_ID,Total_Fare,Payment_Method,driver_Choose,Fuel_Choose,Car_Price_Per_Hr,pickup_Location
            ,destination_Location,StartingTrip_Date,EndingTrip_Date,StartingTrip_Time,EndingTrip_Time,
            HoursTrip_CarRent,CarModal_Name,CarModal_No,Customer_Name,Customer_No,Customer_ID,car_Category,
    Booking_Date,Booking_Time;



    public NewBooking_RentCar_Information_Helper_UsersApp()
    {

    }

    public NewBooking_RentCar_Information_Helper_UsersApp(String car_owner_ID, String booking_Date, String booking_Time,
                                                          String total_Fare, String payment_Method, String driver_Choose, String fuel_Choose,
                                                          String car_Price_Per_Hr, String pickup_Location,
                                                          String destination_Location, String StartingTrip_Date,
                                                          String EndingTrip_Date, String StartingTrip_Time, String EndingTrip_Time,
                                                          String hoursTrip_CarRent, String CarModal_Name, String CarModal_No,
                                                          String Customer_Name, String Customer_No, String Customer_ID,
                                                          String car_Category)

    {
        this.car_owner_ID = car_owner_ID;
        this.Booking_Date = booking_Date;
        this.Booking_Time = booking_Time;

        this.Total_Fare = total_Fare;
        this.Payment_Method = payment_Method;
        this.driver_Choose = driver_Choose;
        this.Fuel_Choose = fuel_Choose;
        this.Car_Price_Per_Hr = car_Price_Per_Hr;
        this.pickup_Location = pickup_Location;
        this.destination_Location = destination_Location;

        this.StartingTrip_Date = StartingTrip_Date;
        this.StartingTrip_Time = StartingTrip_Time;

        this.EndingTrip_Date = EndingTrip_Date;
        this.EndingTrip_Time = EndingTrip_Time;

        this.HoursTrip_CarRent = hoursTrip_CarRent;

        this.CarModal_Name=CarModal_Name;
        this.CarModal_No=CarModal_No;

        this.Customer_Name=Customer_Name;
        this.Customer_No=Customer_No;

        this.Customer_ID=Customer_ID;
        this.car_Category=car_Category;


    }

    public String getBooking_Time() {
        return Booking_Time;
    }

    public void setBooking_Time(String booking_Time) {
        Booking_Time = booking_Time;
    }

    public String getStartingTrip_Time() {
        return StartingTrip_Time;
    }

    public void setStartingTrip_Time(String startingTrip_Time) {
        StartingTrip_Time = startingTrip_Time;
    }

    public String getEndingTrip_Time() {
        return EndingTrip_Time;
    }

    public void setEndingTrip_Time(String endingTrip_Time) {
        EndingTrip_Time = endingTrip_Time;
    }

    public String getCarModal_No() {
        return CarModal_No;
    }

    public void setCarModal_No(String carModal_No) {
        CarModal_No = carModal_No;
    }

    public String getCustomer_No() {
        return Customer_No;
    }

    public void setCustomer_No(String customer_No) {
        Customer_No = customer_No;
    }

    public String getCar_Category() {
        return car_Category;
    }

    public void setCar_Category(String car_Category) {
        this.car_Category = car_Category;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getCarModal_Name() {
        return CarModal_Name;
    }

    public void setCarModal_Name(String carModal_Name) {
        CarModal_Name = carModal_Name;
    }

    public String getCar_owner_ID() {
        return car_owner_ID;
    }

    public void setCar_owner_ID(String userID_owner) {
        this.car_owner_ID = userID_owner;
    }

    public String getBooking_Date() {
        return Booking_Date;
    }

    public void setBooking_Date(String booking_Date) {
        this.Booking_Date = booking_Date;
    }

    public String getTotal_Fare() {
        return Total_Fare;
    }

    public void setTotal_Fare(String total_Fare) {
        Total_Fare = total_Fare;
    }

    public String getPayment_Method() {
        return Payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        Payment_Method = payment_Method;
    }

    public String getDriver_Choose() {
        return driver_Choose;
    }

    public void setDriver_Choose(String driver_Choose) {
        this.driver_Choose = driver_Choose;
    }

    public String getFuel_Choose() {
        return Fuel_Choose;
    }

    public void setFuel_Choose(String fuel_Choose) {
        Fuel_Choose = fuel_Choose;
    }

    public String getCar_Price_Per_Hr() {
        return Car_Price_Per_Hr;
    }

    public void setCar_Price_Per_Hr(String car_Price_Per_Hr) {
        Car_Price_Per_Hr = car_Price_Per_Hr;
    }

    public String getPickup_Location() {
        return pickup_Location;
    }

    public void setPickup_Location(String pickup_Location) {
        this.pickup_Location = pickup_Location;
    }

    public String getDestination_Location() {
        return destination_Location;
    }

    public void setDestination_Location(String destination_Location) {
        this.destination_Location = destination_Location;
    }

    public String getStartingTrip_Date() {
        return StartingTrip_Date;
    }

    public void setStartingTrip_Date(String startingTrip_Date) {
        this.StartingTrip_Date = startingTrip_Date;
    }

    public String getEndingTrip_Date() {
        return EndingTrip_Date;
    }

    public void setEndingTrip_Date(String endingTrip_Date) {
        EndingTrip_Date = endingTrip_Date;
    }

    public String getHoursTrip_CarRent() {
        return HoursTrip_CarRent;
    }

    public void setHoursTrip_CarRent(String hoursTrip_CarRent) {
        HoursTrip_CarRent = hoursTrip_CarRent;
    }
}
