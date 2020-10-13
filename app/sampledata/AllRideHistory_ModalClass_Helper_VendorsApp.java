package com.example.jameel.everica.VendorApp.Helpers;

/**
 * Created by jameel on 4/16/2020.
 */

public class AllRideHistory_ModalClass_Helper_VendorsApp {


    String field_2,field_3,field_4,field_5,field_6;

    int image_car;


    public AllRideHistory_ModalClass_Helper_VendorsApp(int image_car, String field_2, String field_3, String field_4, String field_5, String field_6)
   // public AllRideHistory_ModalClass_Helper_VendorsApp(String field_2, String field_3, String field_4, String field_5, String field_6)

    {

       this.image_car = image_car;
        this.field_2 = field_2;
        this.field_3 = field_3;
        this.field_4 = field_4;
        this.field_5 = field_5;
        this.field_6 = field_6;
    }


    public int getImage_car() {
        return image_car;
    }

    public void setImage_car(int image_car) {
        this.image_car = image_car;
    }




    public String getField_2() {
        return field_2;
    }

    public void setField_2(String field_2) {
        this.field_2 = field_2;
    }

    public String getField_3() {
        return field_3;
    }

    public void setField_3(String field_3) {
        this.field_3 = field_3;
    }

    public String getField_4() {
        return field_4;
    }

    public void setField_4(String field_4) {
        this.field_4 = field_4;
    }

    public String getField_5() {
        return field_5;
    }

    public void setField_5(String field_5) {
        this.field_5 = field_5;
    }

    public String getField_6() {
        return field_6;
    }

    public void setField_6(String field_6) {
        this.field_6 = field_6;
    }
}
