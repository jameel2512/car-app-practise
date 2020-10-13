package com.example.jameel.everica.VendorApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Current_Booking_Payment_Collect_UsersApp;
import com.example.jameel.everica.UsersApp.Helpers.Current_Booking_Payment_Collect_Helper_UsersApp;
import com.example.jameel.everica.VendorApp.Helpers.Current_Booking_Payment_Collect_Helper_VendorsApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Current_Booking_Payment_Collect_VendorsApp extends AppCompatActivity {



    TextView textView_totalfare;
    EditText editText_farerecieved;

    Button button_paidFare;
    RatingBar ratingBar_ratevendor;

    String rateing_Vendor;
    String total_fare,fare_Recieved,start_Date,start_Time,end_Date,end_Time,car_modal_no,car_modal_name,
            Customer_ID,car_Owner_ID,Wallet;

    int Vendor_Wallet,Customer_Wallet;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference_Users,databaseReference_Vendors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_booking_payment_collect_vendors_app);




        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference_Users= FirebaseDatabase.getInstance().getReference("Users");
        databaseReference_Vendors=FirebaseDatabase.getInstance().getReference("Vendors");

        textView_totalfare=findViewById(R.id.id_pay_col_totalfare_tv);
       // editText_farerecieved=findViewById(R.id.id_pay_col_farerecieved_ed);

        button_paidFare=findViewById(R.id.id_pay_col_collectfare_btn);
        ratingBar_ratevendor=findViewById(R.id.id_pay_col_ratingvendor);


        Intent intent=getIntent();


        start_Date=intent.getStringExtra("startdate");
        end_Date=intent.getStringExtra("enddate");
        start_Time=intent.getStringExtra("starttime");
        end_Time=intent.getStringExtra("endtime");

        car_modal_no=intent.getStringExtra("carmodalno");
        car_modal_name=intent.getStringExtra("carmodalname");
        Customer_ID=intent.getStringExtra("customerid");


        try {

            total_fare = intent.getStringExtra("totalfare");
            textView_totalfare.setText("Fare : " + total_fare);

        }
        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }




        button_paidFare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Delete_Mybooking_from_List();

                } catch (Exception e) {

                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

                Collected_Fare_Save();

            }
        });










    }




    private void Collected_Fare_Save() {


        car_Owner_ID=firebaseAuth.getCurrentUser().getUid();

        fare_Recieved=editText_farerecieved.getText().toString();
        rateing_Vendor= String.valueOf(ratingBar_ratevendor.getRating());

        int fareRecieved=Integer.parseInt(fare_Recieved);
        int totalFare = Integer.parseInt(total_fare);

        Vendor_Wallet=totalFare-fareRecieved;

        Wallet= String.valueOf(Vendor_Wallet);

        Current_Booking_Payment_Collect_Helper_VendorsApp payment_collect_helper_vendorsApp=
                new Current_Booking_Payment_Collect_Helper_VendorsApp(total_fare,fare_Recieved,rateing_Vendor
                        ,Customer_ID,car_Owner_ID,Wallet);

        databaseReference_Vendors
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("Current Booking Fare Collected")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .setValue(payment_collect_helper_vendorsApp)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                                    "Fare Stored", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        

//
//        databaseReference_Users
//                .child(Customer_ID)
//                .child("Current Booking Fare Collected")
//                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
//                .setValue(payment_collect_helper_vendorsApp)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        if (task.isSuccessful())
//                        {
//                            Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
//                                    "Fare Stored vendor", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//



        int fareCollect=Integer.parseInt(fare_Recieved);
        int totalfare = Integer.parseInt(total_fare);

        if(fareCollect <= totalfare)
        {
            Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                    "fare amount is less Then Total fare ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                    ""+fareRecieved+" Recieved \n"+Wallet+" added in wallet", Toast.LENGTH_SHORT).show();
        }



    }






    private  void Delete_Mybooking_from_List()
    {


        car_Owner_ID=firebaseAuth.getCurrentUser().getUid();

        databaseReference_Vendors
                .child("Customer Booking Trips Confirmed")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        databaseReference_Vendors
                .child(car_Owner_ID)
                .child("Vendor Booking Trip To Be Complete")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        databaseReference_Users
                .child(Customer_ID)
                .child("Customer Booking Trips Confirmed")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });



        databaseReference_Users
                .child("Customer Booking Trips Confirmed")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });



        databaseReference_Vendors
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("Current_Booking_Running")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        databaseReference_Vendors
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("Current Booking Running Time")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });



        databaseReference_Users
                .child(Customer_ID)
                .child("Current_Booking_Running")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        databaseReference_Users
                .child(Customer_ID)
                .child("Current Booking Running Time")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_VendorsApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }











}
