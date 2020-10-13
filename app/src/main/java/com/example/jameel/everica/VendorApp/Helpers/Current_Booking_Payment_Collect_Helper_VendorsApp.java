package com.example.jameel.everica.VendorApp.Helpers;

/**
 * Created by jameel on 5/30/2020.
 */

public class Current_Booking_Payment_Collect_Helper_VendorsApp {

    String Total_fare,Fare_Recieved,Rating_Vendor,Customer_ID,Vendor_ID,Vendor_Wallet;


    public Current_Booking_Payment_Collect_Helper_VendorsApp(){

    }

    public Current_Booking_Payment_Collect_Helper_VendorsApp(String total_fare, String fare_Recieved,
                                                           String rating_Vendor, String customer_ID, String vendor_ID,String vendor_Wallet)

    {
        Total_fare = total_fare;
        Fare_Recieved = fare_Recieved;
        Rating_Vendor = rating_Vendor;
        Customer_ID = customer_ID;
        Vendor_ID = vendor_ID;
        Vendor_Wallet=vendor_Wallet;
    }


    public String getVendor_Wallet() {
        return Vendor_Wallet;
    }

    public void setVendor_Wallet(String vendor_Wallet) {
        Vendor_Wallet = vendor_Wallet;
    }

    public String getTotal_fare() {
        return Total_fare;
    }

    public void setTotal_fare(String total_fare) {
        Total_fare = total_fare;
    }

    public String getFare_Recieved() {
        return Fare_Recieved;
    }

    public void setFare_Recieved(String fare_Recieved) {
        Fare_Recieved = fare_Recieved;
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
