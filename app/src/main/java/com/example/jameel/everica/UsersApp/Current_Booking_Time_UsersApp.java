package com.example.jameel.everica.UsersApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Helpers.Current_Booking_Time_Helper_UsersApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class Current_Booking_Time_UsersApp extends AppCompatActivity {


    Button button_startpausetime, button_endtime;

    String start_Date,end_Date,start_Time,end_Time,car_Owner_ID,CustomerID,start_Button_Clicked,
    car_modal_name,car_modal_no,booking_date,booking_time,total_fare;

    CountdownView countdownView;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference_Users,databaseReference_Vendors;

//    boolean start_Button_Clicked=false;
    boolean st_Btn_true_or_false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_booking_time_users_app);


        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference_Users= FirebaseDatabase.getInstance().getReference("Users");
        databaseReference_Vendors=FirebaseDatabase.getInstance().getReference("Vendors");


        button_startpausetime = findViewById(R.id.id_cbc_startTrip_btn_users);
        button_endtime = findViewById(R.id.id_cbc_endTrip_btn_users);

        countdownView = (CountdownView) findViewById(R.id.id_cbc_showtime_running_users);

        try{

            Intent intent = getIntent();

            start_Date=intent.getStringExtra("startdate");
            end_Date=intent.getStringExtra("enddate");
            start_Time=intent.getStringExtra("starttime");
            end_Time=intent.getStringExtra("endtime");
            car_Owner_ID=intent.getStringExtra("carownerid");
            booking_date=intent.getStringExtra("booking_date");
            booking_time=intent.getStringExtra("booking_time");
            car_modal_no=intent.getStringExtra("carmodalno");
            car_modal_name=intent.getStringExtra("carmodalname");
            total_fare=intent.getStringExtra("totalfare");




        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




      //  Save_RunningTrip_Time_for_vendors();





        button_startpausetime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){


               // Calculate_Remaning_Time_ToCompleteTrip();

                Save_RunningTrip_Time_for_users();

//                try {
//
//                    StartButtonClicked_TRUE_OR_FALSE();
//
//                } catch (Exception e) {
//
//                    Toast.makeText(Current_Booking_Time_UsersApp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }


                if (start_Button_Clicked == "yes") {

                    Calculate_Remaning_Time_ToCompleteTrip();

                }else {

                    Toast.makeText(Current_Booking_Time_UsersApp.this,
                            "Vendor Did not clicked on Start Trip Button ", Toast.LENGTH_SHORT).show();
                }



            }
        });




        button_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//                Toast.makeText(Current_Booking_Time_UsersApp.this,
//                        "End trip clicked", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Current_Booking_Time_UsersApp.this,
                        Current_Booking_Payment_Collect_UsersApp.class);

                intent.putExtra("totalfare",total_fare);
                intent.putExtra("carownerid",car_Owner_ID);
                intent.putExtra("startdate",start_Date);
                intent.putExtra("enddate",end_Date);
                intent.putExtra("starttime",start_Time);
                intent.putExtra("endtime",end_Time);
                intent.putExtra("carmodalno",car_modal_no);
                intent.putExtra("carmodalname",car_modal_name);
                intent.putExtra("customerid",CustomerID);


                startActivity(intent);


            }
        });



    }






    private void Save_RunningTrip_Time_for_users(){

        try {

            start_Button_Clicked ="yes";

            CustomerID=firebaseAuth.getCurrentUser().getUid();

            Current_Booking_Time_Helper_UsersApp currentTrip_time =
                    new Current_Booking_Time_Helper_UsersApp(start_Date,end_Date,
                            start_Time,end_Time,car_Owner_ID,CustomerID,start_Button_Clicked);


            databaseReference_Users
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child("Current Booking Running Time")
                    .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                    .setValue(currentTrip_time)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(Current_Booking_Time_UsersApp.this,
                                        "Time Saved Users", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }





//
//    private  void Save_RunningTrip_Time_for_vendors(){
//
//
//        try {
//
//
//            CustomerID=firebaseAuth.getCurrentUser().getUid();
//
//            Current_Booking_Time_Helper_UsersApp currentTrip_time =
//                    new Current_Booking_Time_Helper_UsersApp(start_Date,end_Date,
//                            start_Time,end_Time,car_Owner_ID,CustomerID,start_Button_Clicked);
//
//
//            databaseReference_Vendors
//                    .child(car_Owner_ID)
//                    .child("Current Booking Running Time")
//                    .setValue(currentTrip_time)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//
//                            if(task.isSuccessful())
//                            {
//                                Toast.makeText(Current_Booking_Time_UsersApp.this,
//                                        "Time Saved Vendors", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//        } catch (Exception e) {
//
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//    }



    private void StartButtonClicked_TRUE_OR_FALSE()
    {

        try {

            databaseReference_Vendors.
                    child(car_Owner_ID)
                    .child("Current Booking Running Time")
                    .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            start_Button_Clicked = dataSnapshot.child("Start_Button_Clicked").getValue().toString();

                            Toast.makeText(Current_Booking_Time_UsersApp.this,
                                    "Start button : "+start_Button_Clicked, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }




    private void Calculate_Remaning_Time_ToCompleteTrip()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try
        {

            String end_datetime = end_Date+" "+end_Time;
            Date date = new Date();
            date=simpleDateFormat.parse(end_datetime);
            long End_Time_Date=date.getTime();


            String start_datetime=start_Date+" "+start_Time;
            Date date2 = new Date();
            date2=simpleDateFormat.parse(start_datetime);
            long start_DT=date2.getTime();
            long calculate_datetime = End_Time_Date - start_DT;

            countdownView.start(calculate_datetime);


        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }








}
