package com.example.jameel.everica.UsersApp;

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
import com.example.jameel.everica.UsersApp.Helpers.Current_Booking_Payment_Collect_Helper_UsersApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Current_Booking_Payment_Collect_UsersApp extends AppCompatActivity {

    TextView textView_totalfare;
   // EditText editText_paidfare;

    Button button_paidfare;
    RatingBar ratingBar_ratevendor;

    String rateing_Vendor;
    String total_fare,start_Date,start_Time,end_Date,end_Time,car_modal_no,car_modal_name,
            car_Owner_ID,Customer_ID,paid_fare_Customer,Wallet;

    String customer_Wallet;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference_Users,databaseReference_Vendors;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_booking_payment_collect_users_app);



        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference_Users= FirebaseDatabase.getInstance().getReference("Users");
        databaseReference_Vendors=FirebaseDatabase.getInstance().getReference("Vendors");

        textView_totalfare=findViewById(R.id.id_pay_col_totalfare_tv);

        //  editText_farerecieved=findViewById(R.id.id_pay_col_farerecieved_ed);

        button_paidfare=findViewById(R.id.id_pay_col_paidfare_btn);
        ratingBar_ratevendor=findViewById(R.id.id_pay_col_ratingvendor);


        Intent intent=getIntent();


        start_Date=intent.getStringExtra("startdate");
        end_Date=intent.getStringExtra("enddate");
        start_Time=intent.getStringExtra("starttime");
        end_Time=intent.getStringExtra("endtime");

        car_modal_no=intent.getStringExtra("carmodalno");
        car_modal_name=intent.getStringExtra("carmodalname");
        car_Owner_ID=intent.getStringExtra("carownerid");
        Customer_ID=intent.getStringExtra("customerid");


        try {

            total_fare = intent.getStringExtra("totalfare");
            textView_totalfare.setText("Fare :" + total_fare);

        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        try {

        databaseReference_Vendors
                .child(car_Owner_ID)
                .child("Current Booking Fare Collected")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        paid_fare_Customer=dataSnapshot.child("Fare_Recieved").getValue().toString();
                        customer_Wallet = dataSnapshot.child("Vendor_Wallet").getValue().toString();

                        Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                                "paid fare is "+paid_fare_Customer, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }catch (Exception e)
        {

         Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();


        }








        button_paidfare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Delete_Mybooking_from_List();

                } catch (Exception e) {

                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                Paid_Fare_Save();

            }
        });






    }








    private void Paid_Fare_Save() {

        //fare_Recieved=editText_farerecieved.getText().toString();



        rateing_Vendor= String.valueOf(ratingBar_ratevendor.getRating());

        String customer_id=firebaseAuth.getCurrentUser().getUid();

        Wallet=Wallet + customer_Wallet;

        Current_Booking_Payment_Collect_Helper_UsersApp payment_collect_helper_usersApp=
                new Current_Booking_Payment_Collect_Helper_UsersApp(total_fare,paid_fare_Customer,rateing_Vendor
                        ,customer_id,car_Owner_ID,Wallet);

        databaseReference_Users
                 .child(firebaseAuth.getCurrentUser().getUid())
                .child("Current Booking Fare Collected")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .setValue(payment_collect_helper_usersApp)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                                    "Fare Stored", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


//
//        databaseReference_Vendors
//                .child(car_Owner_ID)
//                .child("Current Booking Fare Collected")
//                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
//                .setValue(payment_collect_helper_usersApp)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                        if (task.isSuccessful())
//                        {
//                            Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
//                                    "Fare Stored vendor", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//


//        int fareCollect=Integer.parseInt(fare_Recieved);
//        int totalfare = Integer.parseInt(total_fare);
//
//        if(fareCollect <= totalfare)
//        {
//            Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
//                    "fare amount is less Then Total fare ", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
//                    "fare Recieved ", Toast.LENGTH_SHORT).show();
//        }



    }






    private  void Delete_Mybooking_from_List()
    {

        databaseReference_Users
                .child("Customer Booking Trips Confirmed")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        databaseReference_Users
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("Customer Booking Trips Confirmed")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
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
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });



        databaseReference_Vendors
                .child("Customer Booking Trips Confirmed")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });



        databaseReference_Users
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("Current_Booking_Running")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        databaseReference_Users
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("Current Booking Running Time")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });





        databaseReference_Vendors
                .child(car_Owner_ID)
                .child("Current_Booking_Running")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        databaseReference_Vendors
                .child(car_Owner_ID)
                .child("Current Booking Running Time")
                .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(Current_Booking_Payment_Collect_UsersApp.this,
                            "Deleted booking ", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }



}
