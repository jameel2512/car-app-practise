package com.example.jameel.everica.UsersApp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.TimeZoneFormat;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Helpers.LocationTimeDate_helper_UsersApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.PeriodType;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Location_And_DateTime_Activity_UsersApp extends AppCompatActivity {

    EditText editText_start_location, editText_end_location, editText_hours;

    TextView textView_startDate, textView_endDate, textView_startTime, textView_endTime;

    Button button_startDate, button_endDate, button_startTime, button_endTime,
            button_saveInformation;


    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;


    private String startDate, endDate, startTime, endTime, pickupLocation, destinationLocation, hoursForRent;

    private String currDate, currTime;

    int i = 1;
    private int j;

    private String dateText_start, dateText_end, timeText_start, timeText_end;

    private int hours, minutes, year, month, day;


    EditText editText_inputtime;
    TextView textView_showtime;

    Button button_startpausetime, button_resettime, button_settime;

    CountDownTimer countDownTimer;

    Boolean mTimerRunning;

    long mStartTimeInMillis, mTimeLeftInMillis;
    private long mEndTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location__and__date_time___users_app);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");


        editText_end_location = (EditText) findViewById(R.id.id_ldt_end_location);
        editText_start_location = (EditText) findViewById(R.id.id_ldt_start_location);
        editText_hours = (EditText) findViewById(R.id.id_ldt_hoursforRentCar);

        textView_endDate = (TextView) findViewById(R.id.id_ldt_endDate_tv);
        textView_startDate = (TextView) findViewById(R.id.id_ldt_startDate_tv);
        textView_startTime = (TextView) findViewById(R.id.id_ldt_startTime_tv);
        textView_endTime = (TextView) findViewById(R.id.id_ldt_endTime_tv);

        button_startDate = (Button) findViewById(R.id.id_ldt_startDate_btn);
        button_endDate = (Button) findViewById(R.id.id_ldt_endDate_btn);
        button_startTime = (Button) findViewById(R.id.id_ldt_startTime_btn);
        button_endTime = (Button) findViewById(R.id.id_ldt_endTime_btn);

        button_saveInformation = findViewById(R.id.id_ldt_dateTime_location_Save_btn);


//        editText_inputtime = findViewById(R.id.id_ldt_inputtime);
//        textView_showtime = findViewById(R.id.id_ldt_showtime);
//        button_settime = findViewById(R.id.id_ldt_setTime_btn);
//        button_startpausetime = findViewById(R.id.id_ldt_startTime_btnnew);
//        button_resettime = findViewById(R.id.id_ldt_resetTime_btnnew);


        button_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTimeHandle();
            }
        });


        button_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EndTimeHandle();
            }
        });


        button_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartDateHandle();
            }
        });


        button_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EndDateHandle();
            }
        });


        button_saveInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SroreInformaton();

            }
        });





//
//        button_settime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v){
//
//
//                Calculate_Date_And_Time();
//
//
//
//                String inputTime = editText_inputtime.getText().toString();
//
////                String input = editText_inputtime.getText().toString();
////                if (input.length() == 0) {
////                    Toast.makeText(Location_And_DateTime_Activity_UsersApp.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
////                    return;
////                }
//                long millisInput = Long.parseLong(inputTime) * 60000;
////                if (millisInput == 0) {
////                    Toast.makeText(Location_And_DateTime_Activity_UsersApp.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////
//                setTime(millisInput);
//
//                //editText_inputtime.setText("");
//
//
//            }
//        });
//
//
//
//
//        button_startpausetime.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick (View v){
//
//                if (mTimerRunning) {
//
//                    pauseTimer();
//
//                } else {
//
//                    startTimer();
//                }
//            }
//        });
//
//
//        button_resettime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick (View v){
//                resetTimer();
//            }
//        });
//
//
//







    }














    private void SroreInformaton() {


        startDate = textView_startDate.getText().toString();
        endDate = textView_endDate.getText().toString();
        startTime = textView_startTime.getText().toString();
        endTime = textView_endTime.getText().toString();
        pickupLocation = editText_start_location.getText().toString();
        destinationLocation = editText_end_location.getText().toString();
        hoursForRent = editText_hours.getText().toString();


        try {

            Calendar calendar_Date = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            currDate = simpleDateFormat.format(calendar_Date.getTime());

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


        try {

            Calendar calendar_Time = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm:ss");
            currTime = simpleDateFormat1.format(calendar_Time.getTime());

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


        LocationTimeDate_helper_UsersApp locationDateTime_helper = new LocationTimeDate_helper_UsersApp();

        locationDateTime_helper = new LocationTimeDate_helper_UsersApp(startDate
                , endDate, startTime, endTime, pickupLocation, destinationLocation, hoursForRent);


        try {

            databaseReference
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child("Location Date Time Information")
                    .child(currDate + " " + i)
                    // .child(currDate+" "+currTime)
                    .setValue(locationDateTime_helper)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Intent intent = new Intent(Location_And_DateTime_Activity_UsersApp.this,
                                        DashBoard_UsersApp.class);
                                startActivity(intent);

                                Toast.makeText(Location_And_DateTime_Activity_UsersApp.this, "Data Stored", Toast.LENGTH_SHORT).show();


                            } else {

                                Toast.makeText(Location_And_DateTime_Activity_UsersApp.this, "Data not stored", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


       // Calculate_Date_And_Time();


    }






    private void StartDateHandle() {


        Calendar calendar = Calendar.getInstance();

        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
//                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                dateText_start = DateFormat.format("dd-MM-yyyy", calendar1).toString();

                textView_startDate.setText(dateText_start);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();


    }







    private void EndDateHandle() {


        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);

                dateText_end = DateFormat.format("dd-MM-yyyy", calendar1).toString();

                textView_endDate.setText(dateText_end);

            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }







    private void StartTimeHandle() {


        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);

                timeText_start = DateFormat.format("hh:mm:ss", calendar1).toString();

                textView_startTime.setText(timeText_start);

            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();


    }





    private void EndTimeHandle() {



        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);

                timeText_end = DateFormat.format("hh:mm:ss", calendar1).toString();

                textView_endTime.setText(timeText_end);

            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();


    }








//
//
//    private void Calculate_Date_And_Time() {
//
//        try {
//
//            try {
//
//                SimpleDateFormat simpleDateFormat_year_month_day = new SimpleDateFormat("dd-MM-yyyy");
//
//                Date date1 = simpleDateFormat_year_month_day.parse(dateText_start);
//                Date date2 = simpleDateFormat_year_month_day.parse(dateText_end);
//
//                long starting_date = date1.getTime();
//                long ending_date = date2.getTime();
//
//                if (starting_date <= ending_date) {
//                    org.joda.time.Period period = new org.joda.time.Period(starting_date, ending_date,
//                            PeriodType.yearMonthDay());
//
//                    year = period.getYears();
//                    month = period.getMonths();
//                    day = period.getDays();
//
//                    Toast.makeText(this, "day " + day + " - Month: " + month + " - year " + year, Toast.LENGTH_LONG).show();
//                }
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//            try {
//
//                SimpleDateFormat simpleDateFormat_year_month_day = new SimpleDateFormat("hh:mm:ss");
//
//                Date time1 = simpleDateFormat_year_month_day.parse(timeText_start);
//                Date time2 = simpleDateFormat_year_month_day.parse(timeText_end);
//
//                long starting_time = time1.getTime();
//                long ending_time = time2.getTime();
//
//                if (starting_time <= ending_time) {
//
//                    org.joda.time.Period period = new org.joda.time.Period(starting_time, ending_time);  //,// PeriodType.hours());
//
//                     hours = period.getHours();
//                     minutes = period.getMinutes();
//
//                    Toast.makeText(this,"Before time : hours "+hours+" : minutes : "+minutes,Toast.LENGTH_LONG).show();
//
//
//
//                    int days1= day * 1440;
//                    int months1= month * 43200;
//
//
//                    int hours1 = hours * 60;
//
//                    //int hours_cal = hours1 + minutes;
//
//                    int hours_cal = hours1 + minutes + days1 + months1;
//
//                    String hours_minutes = String.valueOf(hours_cal);
//
//                    Toast.makeText(Location_And_DateTime_Activity_UsersApp.this,
//                            "Time in Minutes: "+hours_cal,Toast.LENGTH_SHORT).show();
//
//                    editText_inputtime.setText(hours_minutes);
//
//                }
//
//            }
//            catch (ParseException e) {
//
//                e.printStackTrace();
//
//            }
//
//
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//
//
////        try{
////
////            CalculateTimeInMinutes();
////
////        } catch (Exception e) {
////            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
////        }
//
//
//    }
//
//
//
//
//
//    private void CalculateTimeInMinutes()
//    {
//
//    }
//
//
//
//
//    private void setTime(long milliseconds) {
//        mStartTimeInMillis = milliseconds;
//        resetTimer();
//        closeKeyboard();
//    }
//
//    private void closeKeyboard() {
//
//        View view = this.getCurrentFocus();
//        if (view != null) {
//
//            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
//
//
//
//
//
//    private void resetTimer() {
//        mTimeLeftInMillis = mStartTimeInMillis;
//        updateCountDownText();
//
//
//        updateWatchInterface();
//    }
//
//
//    private void pauseTimer() {
//        countDownTimer.cancel();
//        mTimerRunning = false;
//        updateWatchInterface();
//    }
//
//
//    private void updateCountDownText() {
//        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
//        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//        String timeLeftFormatted;
//        if (hours > 0) {
//            timeLeftFormatted = String.format(Locale.getDefault(),
//                    "%d:%02d:%02d", hours, minutes, seconds);
//        } else {
//            timeLeftFormatted = String.format(Locale.getDefault(),
//                    "%02d:%02d", minutes, seconds);
//        }
//        textView_showtime.setText(timeLeftFormatted);
//    }
//
//
//
//
//
//
//
//    private void startTimer() {
//        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
//        countDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//            @Override
//            public void onFinish() {
//                mTimerRunning = false;
//                updateWatchInterface();
//            }
//        }.start();
//        mTimerRunning = true;
//        updateWatchInterface();
//    }
//
//
//
//
//
//    private void updateWatchInterface() {
//
//        if (mTimerRunning) {
//            editText_inputtime.setVisibility(View.INVISIBLE);
//            button_settime.setVisibility(View.INVISIBLE);
//            button_resettime.setVisibility(View.INVISIBLE);
//            button_startpausetime.setText("Pause");
//        } else {
//            editText_inputtime.setVisibility(View.VISIBLE);
//            button_settime.setVisibility(View.VISIBLE);
//            button_startpausetime.setText("Start");
//            if (mTimeLeftInMillis < 1000) {
//                button_startpausetime.setVisibility(View.INVISIBLE);
//            } else {
//                button_startpausetime.setVisibility(View.VISIBLE);
//            }
//            if (mTimeLeftInMillis < mStartTimeInMillis) {
//                button_resettime.setVisibility(View.VISIBLE);
//            } else {
//                button_resettime.setVisibility(View.INVISIBLE);
//            }
//        }
//    }
//
//
//
//
//
//
//
//
//
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putLong("startTimeInMillis", mStartTimeInMillis);
//        editor.putLong("millisLeft", mTimeLeftInMillis);
//        editor.putBoolean("timerRunning", mTimerRunning);
//        editor.putLong("endTime", mEndTime);
//        editor.apply();
//        if (countDownTimer != null) {
//            countDownTimer.cancel();
//        }
//    }
//
//
//
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
//        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
//        mTimerRunning = prefs.getBoolean("timerRunning", false);
//        updateCountDownText();
//        updateWatchInterface();
//        if (mTimerRunning) {
//            mEndTime = prefs.getLong("endTime", 0);
//            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
//            if (mTimeLeftInMillis < 0) {
//                mTimeLeftInMillis = 0;
//                mTimerRunning = false;
//                updateCountDownText();
//                updateWatchInterface();
//            } else {
//                startTimer();
//            }
//        }
//    }
//
//







}
