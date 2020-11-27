package com.example.jameel.everica.UsersApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Helpers.Current_Booking_Details_Show_Helper_UsersApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewBooking_Details_Show_UsersApp extends AppCompatActivity {


    TextView textView_hours,textView_endDateTime,textView_booking_date
            ,textView_pickupLoation,textView_payment_method,textView_start_DateTime;

    TextView  textView_carModal_Name_No, textView_fuel_choose,
            textView_driver_choose,textView_customer_name_no,textView_totalFare;

    Button button_booking;

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference_Users;


    private String car_owner_ID,customer_ID,pickup_location,destination_location,booking_Date,booking_time
            ,starting_trip_date,starting_trip_time,ending_trip_date,ending_trip_time, total_fare,fuel_choose,
            payment_method,driver_choose, carmodal_name,carmodal_no,customer_name,customer_no,hours_forRents,
            car_Price_Perhour,car_Category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbooking_details_show_users_app);




        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference_Users= FirebaseDatabase.getInstance().getReference("Users");



        textView_start_DateTime=(TextView) findViewById(R.id.id_mybookingdetail_startingDatetime);
        textView_hours=(TextView)findViewById(R.id.id_mybookingdetail_hours);


        textView_carModal_Name_No=(TextView)findViewById(R.id.id_mybookingdetail_carmodal_name_no);
        textView_customer_name_no=(TextView)findViewById(R.id.id_mybookingdetail_customer_name_no);

        textView_totalFare=(TextView)findViewById(R.id.id_mybookingdetail_totalfare);

        textView_fuel_choose=(TextView)findViewById(R.id.id_mybookingdetail_fuelchoose);
        textView_driver_choose=(TextView)findViewById(R.id.id_mybookingdetail_driverchoose);
        textView_payment_method=(TextView)findViewById(R.id.id_mybookingdetail_paymentmethod);
        textView_pickupLoation=(TextView)findViewById(R.id.id_mybookingdetail_pickuploacation);

        textView_booking_date=(TextView)findViewById(R.id.id_mybookingdetail_bookingDatetime);

        textView_endDateTime=(TextView)findViewById(R.id.id_mybookingdetail_endingDatetime);

        button_booking=(Button)findViewById(R.id.id_mybookingdetail_confirmbooking_btn);



        try {

            Bundle intent=getIntent().getExtras();

            car_owner_ID = intent.getString("car_owner_id");
            customer_ID = intent.getString("customer_id");

            customer_name = intent.getString("customer_name");
            customer_no = intent.getString("customer_no");

            carmodal_name = intent.getString("carmodal_name");
            carmodal_no = intent.getString("carmodal_no");

            pickup_location = intent.getString("pickup_location");

            booking_Date = intent.getString("booking_date");
            booking_time = intent.getString("booking_time");

            starting_trip_date = intent.getString("starting_date");
            starting_trip_time = intent.getString("starting_time");

            ending_trip_date = intent.getString("ending_date");
            ending_trip_time = intent.getString("ending_time");

            payment_method = intent.getString("payment_method_choose");
            driver_choose = intent.getString("driver_choose");
            fuel_choose = intent.getString("fuel_choose");
            total_fare = intent.getString("total_fare");
            hours_forRents = intent.getString("hours");

            car_Category= intent.getString("car_category");
            car_Price_Perhour=intent.getString("car_price_per_hour");

        }catch (Exception e)
        {
            Toast.makeText(this, "intent: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }


//        Toast.makeText(this, "starting date time: "+starting_trip_date+" "+starting_trip_time, Toast.LENGTH_SHORT).show();
//
//
//        Toast.makeText(this, "Hours  : "+hours_forRents, Toast.LENGTH_SHORT).show();


        try{

            textView_booking_date.setText( booking_Date);

            textView_endDateTime.setText(ending_trip_date+" "+ending_trip_time);

            textView_customer_name_no.setText(customer_name +" \n " +customer_no);
            textView_carModal_Name_No.setText(carmodal_name+" "+carmodal_no);

            textView_hours.setText(hours_forRents);
            textView_pickupLoation.setText(pickup_location);

            textView_start_DateTime.setText(starting_trip_date+" "+starting_trip_time);

            textView_totalFare.setText(total_fare);
            textView_fuel_choose.setText(fuel_choose);
            textView_driver_choose.setText(driver_choose);
            textView_payment_method.setText(payment_method);
            textView_totalFare.setText(total_fare);


        }catch (Exception e)
        {
            Toast.makeText(this, "showing result : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }










        button_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    ConfirmBooking_Current();

                }catch (Exception e)
                {
                    Toast.makeText(NewBooking_Details_Show_UsersApp.this,
                            e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });











    }



    private void ConfirmBooking_Current()
    {

        Current_Booking_Details_Show_Helper_UsersApp currentBooking =
                new Current_Booking_Details_Show_Helper_UsersApp(car_owner_ID,booking_Date,
                total_fare,payment_method,driver_choose,fuel_choose,car_Price_Perhour,pickup_location,destination_location,
                starting_trip_date,ending_trip_date,starting_trip_time,ending_trip_time,hours_forRents,
                carmodal_name,carmodal_no,customer_name,customer_no,customer_ID,car_Category);

        databaseReference_Users
                .child(firebaseAuth.getCurrentUser().getUid())
                .child("Current_Booking_Running")
                .child(starting_trip_date+" "+starting_trip_time+" "+carmodal_name+" "+carmodal_no+"   "+car_owner_ID)
                .setValue(currentBooking)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(NewBooking_Details_Show_UsersApp.this,
                                    "Current Booking Data Stored", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(NewBooking_Details_Show_UsersApp.this
                                    ,Current_Booking_Time_UsersApp.class);

                            intent.putExtra("startdate",starting_trip_date);
                            intent.putExtra("enddate",ending_trip_date);
                            intent.putExtra("starttime",starting_trip_time);
                            intent.putExtra("endtime",ending_trip_time);
                            intent.putExtra("carownerid",car_owner_ID);
                            intent.putExtra("booking_date",booking_Date);
                            intent.putExtra("booking_time",booking_time);
                            intent.putExtra("carmodalno",carmodal_no);
                            intent.putExtra("carmodalname",carmodal_name);
                            intent.putExtra("totalfare",total_fare);


                            startActivity(intent);
                        }
                    }
                });




    }










}
