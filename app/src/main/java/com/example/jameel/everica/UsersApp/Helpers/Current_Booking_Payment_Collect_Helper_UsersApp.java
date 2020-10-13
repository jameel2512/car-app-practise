package com.example.jameel.everica.UsersApp.Helpers;

/**
 * Created by jameel on 5/30/2020.
 */

public class Current_Booking_Payment_Collect_Helper_UsersApp {

    String Total_fare,Paid_Fare,Rating_Vendor,Customer_ID,Vendor_ID,Customer_Wallet;


    public Current_Booking_Payment_Collect_Helper_UsersApp(){

    }

    public Current_Booking_Payment_Collect_Helper_UsersApp(String total_fare, String paid_Fare,
                                                           String rating_Vendor, String customer_ID, String vendor_ID
            ,String customer_Wallet)

    {
        Total_fare = total_fare;
        Paid_Fare = paid_Fare;
        Rating_Vendor = rating_Vendor;
        Customer_ID = customer_ID;
        Vendor_ID = vendor_ID;
        Customer_Wallet=customer_Wallet;
    }

    public String getPaid_Fare() {
        return Paid_Fare;
    }

    public void setPaid_Fare(String paid_Fare) {
        Paid_Fare = paid_Fare;
    }

    public String getCustomer_Wallet() {
        return Customer_Wallet;
    }

    public void setCustomer_Wallet(String customer_Wallet) {
        Customer_Wallet = customer_Wallet;
    }

    public String getTotal_fare() {
        return Total_fare;
    }

    public void setTotal_fare(String total_fare) {
        Total_fare = total_fare;
    }

    public String getRating_Vendor() {
        return Rating_Vendor;
    }

    public void setRating_Vendor(String rating_Vendor) {
        Rating_Vendor = rating_Vendor;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String customer_ID) {
        Customer_ID = customer_ID;
    }

    public String getVendor_ID() {
        return Vendor_ID;
    }

    public void setVendor_ID(String vendor_ID) {
        Vendor_ID = vendor_ID;
    }
}
