package com.example.jameel.everica.VendorApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jameel.everica.R;

public class DashBoard_VendorApp extends AppCompatActivity {


    Button button_currentRide,button_allRideHistory;

    Button button_addRentCarInfo,button_my_bookings_to_be_complete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_vendor_app);



//        button_allRideHistory=(Button) findViewById(R.id.id_allrideHistory_btn);
//        button_currentRide=(Button) findViewById(R.id.id_currentride_btn);


        button_addRentCarInfo=(Button) findViewById(R.id.id_addRentCarInformation_btn);
        button_my_bookings_to_be_complete=(Button) findViewById(R.id.id_my_bookings_to_be_complete_btn);


        button_addRentCarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DashBoard_VendorApp.this,Adding_RentCar_Information_VendorApp.class);
                startActivity(intent);

            }
        });



        button_my_bookings_to_be_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(DashBoard_VendorApp.this,NewBooking_List_VendorApp.class);
                startActivity(intent);

            }
        });

//
//
//        button_currentRide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(DashBoard_VendorApp.this,CurrentRideActivity_VendorApp.class);
//                startActivity(intent);
//
//
//            }
//        });
//
//
//
//
//        button_allRideHistory.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(DashBoard_VendorApp.this,AllRideHistoryActivity_VendorApp.class);
//                startActivity(intent);
//            }
//        });
//
//






    }








}
