package com.example.jameel.everica.VendorApp.Helpers;

/**
 * Created by jameel on 4/9/2020.
 */

public class NewBooking_RentCarsShow_Helper_VendorApp {

    public String rc_car_price,rc_car_modalname,rc_car_modalno,
            rc_cartype,rc_car_condition,rc_car_userid,car_owner_ID;
    public String rc_car_image;

    public NewBooking_RentCarsShow_Helper_VendorApp(){

    }


    public NewBooking_RentCarsShow_Helper_VendorApp(String rc_car_price, String rc_car_modalname, String rc_car_modalno,
                                                    String rc_cartype, String rc_car_condition,
                                                    String car_owner_ID ) //,String car_owner_ID)//,String rc_car_image)

    {

        this.rc_car_price = rc_car_price;
        this.rc_car_modalname = rc_car_modalname;
        this.rc_car_modalno = rc_car_modalno;
        this.rc_cartype = rc_cartype;
    //  this.rc_car_image = rc_car_image;
        this.rc_car_condition = rc_car_condition;
       // this.rc_car_userid=user_Id;
        this.car_owner_ID=car_owner_ID;

    }

//    public String getRc_car_image() {
//        return rc_car_image;
//    }
//
//    public void setRc_car_image(String rc_car_image) {
//        this.rc_car_image = rc_car_image;
//    }


    public String getCar_owner_ID() {
        return car_owner_ID;
    }

    public void setCar_owner_ID(String car_owner_ID) {
        this.car_owner_ID = car_owner_ID;
    }

    public String getRc_car_userid() {
        return rc_car_userid;
    }

    public void setRc_car_userid(String rc_car_userid) {
        this.rc_car_userid = rc_car_userid;
    }

    public String getRc_car_price() {
        return rc_car_price;
    }

    public void setRc_car_price(String rc_car_price) {
        this.rc_car_price = rc_car_price;
    }

    public String getRc_car_modalname() {
        return rc_car_modalname;
    }

    public void setRc_car_modalname(String rc_car_modalname) {
        this.rc_car_modalname = rc_car_modalname;
    }

    public String getRc_car_modalno() {
        return rc_car_modalno;
    }

    public void setRc_car_modalno(String rc_car_modalno) {
        this.rc_car_modalno = rc_car_modalno;
    }

    public String getRc_cartype() {
        return rc_cartype;
    }

    public void setRc_cartype(String rc_cartype) {
        this.rc_cartype = rc_cartype;
    }

    public String getRc_car_condition() {
        return rc_car_condition;
    }

    public void setRc_car_condition(String rc_car_condition) {
        this.rc_car_condition = rc_car_condition;
    }


}
