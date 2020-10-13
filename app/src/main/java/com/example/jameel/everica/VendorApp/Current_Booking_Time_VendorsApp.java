package com.example.jameel.everica.VendorApp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Current_Booking_Payment_Collect_UsersApp;
import com.example.jameel.everica.UsersApp.Current_Booking_Time_UsersApp;
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

public class Current_Booking_Time_VendorsApp extends AppCompatActivity {


    EditText editText_inputtime;
    TextView textView_showtime_running,textView_showtime_totaltime;

    Button button_startpausetime, button_endtime, button_settime;

    CountDownTimer countDownTimer;


    Boolean mTimerRunning;

    long mStartTimeInMillis, mTimeLeftInMillis;
    private long mEndTime;
    private String dateText_start, dateText_end, timeText_start, timeText_end;

    private int hours, minutes, year, month, day;
    String start_Date,end_Date,start_Time,end_Time,car_Owner_ID,Customer_ID,car_modal_name,car_modal_no;
    String start_Button_Clicked,booking_date,booking_time,total_fare;

    CountdownView countdownView;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference_Vendors,databaseReference_Users;


    //boolean start_Button_Clicked=false;

    boolean st_Btn_true_or_false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_booking_time_vendors_app);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference_Vendors = FirebaseDatabase.getInstance().getReference("Vendors");
        databaseReference_Users = FirebaseDatabase.getInstance().getReference("Users");


        //   editText_inputtime = findViewById(R.id.id_cbc_inputtime);
        // textView_showtime_running = findViewById(R.id.id_cbc_showtime_running);
        //   textView_showtime_totaltime = findViewById(R.id.id_cbc_showtime_totaltime);
        //  button_settime = findViewById(R.id.id_cbc_settime_btn);

        button_startpausetime = findViewById(R.id.id_cbc_startTrip_btn_vendor);
        button_endtime = findViewById(R.id.id_cbc_endTrip_btn_vendor);

        countdownView = (CountdownView) findViewById(R.id.id_cbc_showtime_running_vendor);


//        try
//        {
//
//            databaseReference_Vendors.child(firebaseAuth.)
//
//
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//


        try {

            Intent intent = getIntent();


            start_Date=intent.getStringExtra("startdate");
            end_Date=intent.getStringExtra("enddate");
            start_Time=intent.getStringExtra("starttime");
            end_Time=intent.getStringExtra("endtime");
            Customer_ID=intent.getStringExtra("customerid");
            booking_date=intent.getStringExtra("booking_date");
            booking_time=intent.getStringExtra("booking_time");
            car_modal_no=intent.getStringExtra("carmodalno");
            car_modal_name=intent.getStringExtra("carmodalname");
            total_fare=intent.getStringExtra("totalfare");


        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        button_startpausetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // Calculate_Remaning_Time_ToCompleteTrip();

                Save_RunningTrip_Time_for_vendors();

//                try{
//
//                StartButtonClicked_TRUE_OR_FALSE();
//
//            } catch (Exception e) {
//
//                Toast.makeText(Current_Booking_Time_VendorsApp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }



                if (start_Button_Clicked == "yes") {

                    Calculate_Remaning_Time_ToCompleteTrip();

                } else {

                    Toast.makeText(Current_Booking_Time_VendorsApp.this,
                            "Customer Did not clicked on Start Trip Button", Toast.LENGTH_SHORT).show();
                }


//                if (mTimerRunning) {
//
//                    pauseTimer();
//
//                } else {

//                    startTimer();
//                }
            }
        });


        button_endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Current_Booking_Time_VendorsApp.this,
                        Current_Booking_Payment_Collect_VendorsApp.class);

                intent.putExtra("totalfare",total_fare);
                intent.putExtra("customerid",Customer_ID);
                intent.putExtra("startdate",start_Date);
                intent.putExtra("enddate",end_Date);
                intent.putExtra("starttime",start_Time);
                intent.putExtra("endtime",end_Time);
                intent.putExtra("carmodalno",car_modal_no);
                intent.putExtra("carmodalname",car_modal_name);


                startActivity(intent);
            }
        });






    }



    private void Save_RunningTrip_Time_for_users(){

        try {

            start_Button_Clicked ="yes";

            Customer_ID=firebaseAuth.getCurrentUser().getUid();

            Current_Booking_Time_Helper_UsersApp currentTrip_time =
                    new Current_Booking_Time_Helper_UsersApp(start_Date,end_Date,
                            start_Time,end_Time,car_Owner_ID,Customer_ID,start_Button_Clicked);


            databaseReference_Vendors
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child("Current Booking Running Time")
                    .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+car_Owner_ID)
                    .setValue(currentTrip_time)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(Current_Booking_Time_VendorsApp.this,
                                        "Time Saved Users", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



    }




    private void StartButtonClicked_TRUE_OR_FALSE()
    {

        try {

            databaseReference_Users.
                    child(Customer_ID)
                    .child("Current Booking Running Time")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            start_Button_Clicked = dataSnapshot.child("Start_Button_Clicked").getValue().toString();

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

        String end_datetime = end_Date+" "+end_Time;

        //   long End_DateTime_of_Trip=Long.parseLong(end_datetime)

        try
        {

            Date date = new Date();
            date=simpleDateFormat.parse(end_datetime);

//            long st_date=Long.parseLong(start_Date);
//            long st_time=Long.parseLong(start_Time);

            long End_Time_Date=date.getTime();

            String start_datetime=start_Date+" "+start_Time;

            Date date2 = new Date();
            date2=simpleDateFormat.parse(start_datetime);

            long start_DT=date2.getTime();

            //long start_DateTime_Trip=Long.parseLong(start_datetime);

            long calculate_datetime = End_Time_Date - start_DT;

            countdownView.start(calculate_datetime);


        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }




    private  void Save_RunningTrip_Time_for_vendors(){


        try {

            car_Owner_ID  = firebaseAuth.getCurrentUser().getUid();

            start_Button_Clicked="yes";

            Current_Booking_Time_Helper_UsersApp currentTrip_time =
                    new Current_Booking_Time_Helper_UsersApp(start_Date,end_Date,
                            start_Time,end_Time,car_Owner_ID,Customer_ID,start_Button_Clicked);


            databaseReference_Vendors
                    .child(car_Owner_ID)
                    .child("Current Booking Running Time")
                    .child(start_Date+" "+start_Time+" "+car_modal_name+" "+car_modal_no+"   "+Customer_ID)
                    .setValue(currentTrip_time)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(Current_Booking_Time_VendorsApp.this,
                                        "Time Saved Vendors", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } catch (Exception e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }








//
//        try {
//
//
//            Current_Booking_Time_Helper_VendorsApp currentTrip_time =
//                    new Current_Booking_Time_Helper_VendorsApp(start_Date,end_Date,
//                            start_Time,end_Time,car_Owner_ID,Customer_ID,"no");
//
//
//            databaseReference_Vendors.child(firebaseAuth.getCurrentUser().getUid())
//                    .child("Current Booking Running Time")
//                    .setValue(currentTrip_time)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//
//                            if(task.isSuccessful())
//                            {
//                                Toast.makeText(Current_Booking_Time_VendorsApp.this,
//                                        "Time Saved", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//        } catch (Exception e) {
//
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//







//
//        button_settime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v){
//
//
//                //Calculate_Date_And_Time();
//
//              //  Calculate_Remaning_Time_ToCompleteTrip();
//
//             //   String inputTime = editText_inputtime.getText().toString();
//
////                String input = editText_inputtime.getText().toString();
////                if (input.length() == 0) {
////                    Toast.makeText(Location_And_DateTime_Activity_UsersApp.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
////                    return;
////                }
//              //  long millisInput = Long.parseLong(inputTime) * 60000;
////                if (millisInput == 0) {
////                    Toast.makeText(Location_And_DateTime_Activity_UsersApp.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
//              //  setTime(millisInput);
//
//                //editText_inputtime.setText("");
//
//
//            }
//        });
//
//
////
//






//        button_resettime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v){
//                resetTimer();
//            }
//        });







    /*

         private void Calculate_Date_And_Time() {

        try {

            try {

                SimpleDateFormat simpleDateFormat_year_month_day = new SimpleDateFormat("dd-MM-yyyy");

                Date date1 = simpleDateFormat_year_month_day.parse(start_Date);
                Date date2 = simpleDateFormat_year_month_day.parse(end_Date);

                long starting_date = date1.getTime();
                long ending_date = date2.getTime();

                if (starting_date <= ending_date) {
                    org.joda.time.Period period = new org.joda.time.Period(starting_date, ending_date,
                            PeriodType.yearMonthDay());

                    year = period.getYears();
                    month = period.getMonths();
                    day = period.getDays();

                    Toast.makeText(this, "day " + day + " - Month: " + month + " - year " + year, Toast.LENGTH_LONG).show();
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


            try {

                SimpleDateFormat simpleDateFormat_year_month_day = new SimpleDateFormat("h:mm a");

                Date time1 = simpleDateFormat_year_month_day.parse(start_Time);
                Date time2 = simpleDateFormat_year_month_day.parse(end_Time);

                long starting_time = time1.getTime();
                long ending_time = time2.getTime();

                if (starting_time <= ending_time) {

                    org.joda.time.Period period = new org.joda.time.Period(starting_time, ending_time);  //,// PeriodType.hours());

                    hours = period.getHours();
                    minutes = period.getMinutes();

                    Toast.makeText(this,"Before time : hours "+hours+" : minutes : "+minutes,Toast.LENGTH_LONG).show();

                    int days1= day * 1440;
                    int months1= month * 43200;
                    int hours1 = hours * 60;

                    //int hours_cal = hours1 + minutes;

                    int hours_cal = hours1 + minutes + days1 + months1;

                    String hours_minutes = String.valueOf(hours_cal);

                    Toast.makeText(Current_Booking_Time_VendorsApp.this,
                            "Time in Minutes: "+hours_cal,Toast.LENGTH_SHORT).show();

                    editText_inputtime.setText(hours_minutes);
                }

            }
            catch (ParseException e) {

                e.printStackTrace();
            }


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


//        try{
//
//            CalculateTimeInMinutes();
//
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }


    }





    private void CalculateTimeInMinutes()
    {

    }




    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }


    private void closeKeyboard() {

        View view = this.getCurrentFocus();
        if (view != null) {

            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }





    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }


    private void pauseTimer() {
        countDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }


    private void updateCountDownText() {

        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;

        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }

        textView_showtime_running.setText(timeLeftFormatted);
    }







    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                updateWatchInterface();
            }
        }.start();
        mTimerRunning = true;
        updateWatchInterface();
    }





    private void updateWatchInterface() {

        if (mTimerRunning) {
            editText_inputtime.setVisibility(View.INVISIBLE);
            button_settime.setVisibility(View.INVISIBLE);
           // button_resettime.setVisibility(View.INVISIBLE);
            button_startpausetime.setText("Pause");
        } else {
            editText_inputtime.setVisibility(View.VISIBLE);
            button_settime.setVisibility(View.VISIBLE);
            button_startpausetime.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                button_startpausetime.setVisibility(View.INVISIBLE);
            } else {
                button_startpausetime.setVisibility(View.VISIBLE);
            }

            //if (mTimeLeftInMillis < mStartTimeInMillis) {
              //  button_resettime.setVisibility(View.VISIBLE);
//            } else {
//                button_resettime.setVisibility(View.INVISIBLE);
//            }


        }
    }










    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }




    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }


*/




}
