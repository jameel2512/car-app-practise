package com.example.jameel.everica.UsersApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Helpers.NewBooking_RentCar_Information_Helper_UsersApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TripFair_And_All_Information_UsersApp extends AppCompatActivity {


    TextView textView_start_DateTime,textView_endDateTime,textView_pickupLoation,textView_hours
            ,textView_totalFare,textView_carModal_Name_No,textView_car_Category;

    RadioButton rb_cash,rb_BankTransfer,rb_creditCard;

    RadioButton rb_withFuel,rb_withoutFuel;

    RadioButton rb_withDriver,rb_withoutDriver;

    RadioGroup rg_payment,rg_fuel,rg_driver;

    RadioButton radioButton;

    Button button_confirmbooking,button_checkamount;


    String currDate,currTime;
    int i=1;

    FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference_Vendors,databaseReference_Users;


    String carmodalName,carmodalNo,carType,carCondtion,carPrice,car_Owner_ID,customer_ID;

    private String startDate,endDate,startTime,endTime,pickupLocation,destinationLocation,hoursForRent;

    String cash,bankTransfer,creditCard,withDriver,withoutDriver,withFuel,withoutFuel,totalFare;



    private int fuelfee1,fuelFee,driverFee,km,carPriceAmount,hours,amount1,totalFareAmount;
    private String fuelfee2,fuelfee3,totalAmount,driverfee1,driverfee2;

    private String driver_choose,payment_Method,fuel_choose;

    private int radioFuelID,radiodriverID,radioPaymentID;
    private RadioButton radioButton_fuel,radioButton_driver,radioButton_payment;

    private int j;

    String customer_name,customer_no;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_fair__and__all__information__show_);



        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference_Vendors= FirebaseDatabase.getInstance().getReference("Vendors");
        databaseReference_Users=FirebaseDatabase.getInstance().getReference("Users");


        textView_pickupLoation=(TextView) findViewById(R.id.id_tripfare_pickuplocation);
        textView_start_DateTime=(TextView) findViewById(R.id.id_tripfare_startTimeDate);
        textView_endDateTime=(TextView) findViewById(R.id.id_tripfare_endTimeDate);
        textView_hours=(TextView) findViewById(R.id.id_tripfare_durationHours);
        textView_totalFare=(TextView) findViewById(R.id.id_tripfare_totalfareAmount);
        textView_carModal_Name_No=(TextView) findViewById(R.id.id_tripfare_carmodalName_No);
        textView_car_Category=(TextView)findViewById(R.id.id_tripfare_car_Category);


        button_confirmbooking=(Button) findViewById(R.id.id_tripfare_confirmbooking_btn);
        button_checkamount=(Button) findViewById(R.id.id_tripfare_checkAmount);



        rb_cash=(RadioButton) findViewById(R.id.id_tripfare_rb_cash);
       rb_BankTransfer=(RadioButton) findViewById(R.id.id_tripfare_rb_bankTransfer);
        rb_creditCard=(RadioButton) findViewById(R.id.id_tripfare_rb_creditCard);

        rb_withDriver=(RadioButton) findViewById(R.id.id_tripfare_rb_withdriver);
       rb_withoutDriver=(RadioButton) findViewById(R.id.id_tripfare_rb_withoutdriver);

        rb_withFuel=(RadioButton) findViewById(R.id.id_tripfare_rb_withfuel);
        rb_withoutFuel=(RadioButton) findViewById(R.id.id_tripfare_rb_withoutfuel);


           rg_driver=(RadioGroup) findViewById(R.id.id_tripfare_rg_driver);
              rg_fuel=(RadioGroup) findViewById(R.id.id_tripfare_rg_fuel);
            rg_payment=(RadioGroup) findViewById(R.id.id_tripfare_rg_payment);


                Bundle bundle = getIntent().getExtras();
                carmodalName = bundle.getString("carname");
                carmodalNo = bundle.getString("carno");
                car_Owner_ID=bundle.getString("carownerid");
                carPrice = bundle.getString("carprice");
                carType = bundle.getString("carcategory");


//                carCondtion = bundle.getString("carcondition");
//        Toast.makeText(this, "user ID: "+carmodalID, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "car Price : "+carPrice, Toast.LENGTH_SHORT).show();


                textView_carModal_Name_No.setText(carmodalName + " " + carmodalNo);
                textView_car_Category.setText(carType);



        try {

            Calendar calendar_Date = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            currDate = simpleDateFormat.format(calendar_Date.getTime());

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }



        try {

            Calendar calendar_Time = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            currTime = simpleDateFormat.format(calendar_Time.getTime());

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }




        try
        {

                    databaseReference_Users.child(firebaseAuth.getCurrentUser().getUid())
                            .child("Location Date Time Information")
                    .child(currDate+" "+i)
                    //  .child(currTime+" "+currTime+"")
                            .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            startDate=dataSnapshot.child("startDate").getValue().toString();
                            endDate=dataSnapshot.child("endDate").getValue().toString();
                            startTime=dataSnapshot.child("startTime").getValue().toString();
                            endTime=dataSnapshot.child("endTime").getValue().toString();
                            pickupLocation=dataSnapshot.child("pickupLocation").getValue().toString();
                            hoursForRent=dataSnapshot.child("hoursForRent").getValue().toString();


                            destinationLocation=dataSnapshot.child("destinationLocation").getValue().toString();


                            textView_start_DateTime.setText(startDate+" "+startTime);
                            textView_endDateTime.setText(endDate+" "+endTime);
                            textView_pickupLoation.setText(pickupLocation);
                            textView_hours.setText(hoursForRent);


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }






        try {

            databaseReference_Users
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child("users profile information")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            customer_name=dataSnapshot.child("name").getValue().toString();
                            customer_no=dataSnapshot.child("contact").getValue().toString();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



                }catch (Exception e)
                {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }




//
//        buttonTimePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment timePicker = new TimePickerFragment();
//                timePicker.show(getSupportFragmentManager(), "time picker");
//            }
//        });
//
//        Button buttonCancelAlarm = findViewById(R.id.button_cancel);
//
//        buttonCancelAlarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cancelAlarm();
//            }
//        });

//
//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
//        {
//
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        c.set(Calendar.MINUTE, minute);
//        c.set(Calendar.SECOND, 0);
//
//       // updateTimeText(c);
//      //  startAlarm(c);
//    }
//
//    private void updateTimeText(Calendar c) {
//
//
//        String timeText = "Alarm set for: ";
//        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
//
//        mTextView.setText(timeText);
//    }





//    private void cancelAlarm() {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlertReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
//
//        alarmManager.cancel(pendingIntent);
//        mTextView.setText("Alarm canceled");
//    }








        rg_driver.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                checkDriverRB();

                switch (i)
                {
                    case R.id.id_tripfare_rb_withdriver:
                        driverFee = 1000;
                         driverfee1= String.valueOf(driverFee);

//                        Toast.makeText(TripFair_And_All_Information_UsersApp.this,
//                                "driver fee : "+driverFee, Toast.LENGTH_SHORT).show();

                       // textView_totalFare.setText(driverfee1);

                        break;

                    case R.id.id_tripfare_rb_withoutdriver:

                        driverFee=0;

                         driverfee2= String.valueOf(driverFee);

//                        Toast.makeText(TripFair_And_All_Information_UsersApp.this,
//                                "driver fee : "+driverFee, Toast.LENGTH_SHORT).show();

                       // textView_totalFare.setText(driverfee2);

                        break;

                }
            }
        });



        rg_fuel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                checkFuelRB();

                switch (i)
                {

                    case R.id.id_tripfare_rb_withfuel:

                         km = 10;
                            fuelFee = 50;
                         fuelfee1 = km * fuelFee;
                        fuelfee2 = String.valueOf(fuelfee1);

//                        Toast.makeText(TripFair_And_All_Information_UsersApp.this,
//                                "fuel fee : " + fuelfee1, Toast.LENGTH_SHORT).show();

                        //textView_totalFare.setText(fuelfee2);

                        break;

                    case R.id.id_tripfare_rb_withoutfuel:

                        fuelFee = 0;
                        fuelfee1=fuelFee;

                        fuelfee3 = String.valueOf(fuelFee);

//                        Toast.makeText(TripFair_And_All_Information_UsersApp.this,
//                                "fuel fee : " + fuelFee, Toast.LENGTH_SHORT).show();

                       // textView_totalFare.setText(fuelfee3);

                        break;

                }
            }
        });



        rg_payment.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                checkPaymentRB();
            }
        });







        button_checkamount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        try {

             carPriceAmount = Integer.parseInt(carPrice);
             hours = Integer.parseInt(hoursForRent);
             amount1 = hours * carPriceAmount;

                totalFareAmount = amount1 + driverFee + fuelfee1;

            totalAmount = String.valueOf(totalFareAmount);

//            Toast.makeText(TripFair_And_All_Information_UsersApp.this,
//                    "Total amount : "+totalFareAmount, Toast.LENGTH_SHORT).show();

            textView_totalFare.setText(totalAmount);

           // totalFare=textView_totalFare.getText().toString();

        }catch (Exception e)
        {
            Toast.makeText(TripFair_And_All_Information_UsersApp.this,
                    ""+e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
            }

        });














        button_confirmbooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BookingConformed();

            }
        });





    }



/*
//    private void startAlarm(Calendar calendar)

    private void setAlarm()
    {


        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        String date= currDate;
        String[] datelist = date.split(",");

        String day = datelist[0] ;
        String month = datelist[1] ;
        String year = datelist[2] ;


//        Calendar calendar = Calendar.getInstance();
//        int YEAR = calendar.get(Calendar.YEAR);
//        int MONTH = calendar.get(Calendar.MONTH);
//        int DATE = calendar.get(Calendar.DATE);
//

        calendar.set(Calendar.YEAR,Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month));
        calendar.set(Calendar.DATE, Integer.parseInt(day));

        String time= currTime;
        String[] timelist = time.split(":");

        String hour = datelist[0] ;
        String minut = datelist[1] ;
       // String year = datelist[2] ;

        calendar.set(Calendar.MONTH,Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month));
        calendar.set(Calendar.DATE, Integer.parseInt(day));


        //String dateText = android.text.format.DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();



        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

//        if (calendar.before(Calendar.getInstance())) {
//            calendar.add(Calendar.DATE, 1);
//        }


        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(currentTimeDate), pendingIntent);
//        }



    }



*/











    private void BookingConformed() {

        customer_ID=firebaseAuth.getCurrentUser().getUid();

        NewBooking_RentCar_Information_Helper_UsersApp booking_information_helper=
                new NewBooking_RentCar_Information_Helper_UsersApp(car_Owner_ID,currDate,currTime,totalAmount,payment_Method
                        ,driver_choose,fuel_choose,carPrice,pickupLocation,destinationLocation,
                        startDate, endDate,startTime,endTime,hoursForRent,
                        carmodalName,carmodalNo, customer_name,customer_no,customer_ID,carType);

/*


        try {

            // final long currentTimeDate =Long.parseLong(currDate+" "+currTime);
            //final long currentTime =Long.parseLong(currTime);


           final int starttime= Integer.parseInt(startTime);


            //final long currentTimeDate =Long.parseLong(currDate+" "+currTime);


              setAlarm();


            Toast.makeText(this, "alarm is set "+starttime, Toast.LENGTH_SHORT).show();


            //setAlarm(currentTimeDate);

        }catch (Exception e)
        {
            Toast.makeText(this, "alarm : "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

*/





        try {



            databaseReference_Users
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child("Customer Booking Trips Confirmed")
                    .child(startDate+" "+startTime+" "+carmodalName+" "+carmodalNo+"   "+car_Owner_ID)
                    .setValue(booking_information_helper)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(TripFair_And_All_Information_UsersApp.this,
                                        "Booking Confrmed", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(TripFair_And_All_Information_UsersApp.this,
                                        NewBookings_List_Activity_UsersApp.class);
                                startActivity(intent);

                            }
                        }
                    });

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        try {

            databaseReference_Users
                    .child("Customer Booking Trips Confirmed")
                    .child(startDate+" "+startTime+" "+carmodalName+" "+carmodalNo+"   "+car_Owner_ID)
                    .setValue(booking_information_helper)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(TripFair_And_All_Information_UsersApp.this,
                                        "Booking Confrmed", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(TripFair_And_All_Information_UsersApp.this,
                                        NewBookings_List_Activity_UsersApp.class);

                                startActivity(intent);

                            }
                        }
                    });

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




        try {

            databaseReference_Vendors
                    .child(car_Owner_ID)
                    .child("Vendor Booking Trip To Be Complete")
                    .child(startDate+" "+startTime+" "+carmodalName+" "+carmodalNo+"   "+customer_ID)
                    .setValue(booking_information_helper)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(TripFair_And_All_Information_UsersApp.this,
                                        "Booking Confrmed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        try {

            databaseReference_Vendors
                    .child("Customer Booking Trips Confirmed")
                    .child(startDate+" "+startTime+" "+carmodalName+" "+carmodalNo+"   "+customer_ID)
                    .setValue(booking_information_helper)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(TripFair_And_All_Information_UsersApp.this,
                                        "Booking Confrmed", Toast.LENGTH_SHORT).show();

//                                Intent intent=new Intent(TripFair_And_All_Information_UsersApp.this,
//                                        NewBookings_List_Activity_UsersApp.class);
//                                startActivity(intent);

                            }
                        }
                    });

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }






    public void checkDriverRB()
    {
         radiodriverID=rg_driver.getCheckedRadioButtonId();

        radioButton_driver=findViewById(radiodriverID);

        driver_choose= radioButton_driver.getText().toString();

       // Toast.makeText(this, "You selected :"+driver_choose, Toast.LENGTH_SHORT).show();

    }



    public void checkFuelRB()
    {

        radioFuelID=rg_fuel.getCheckedRadioButtonId();

        radioButton_fuel=findViewById(radioFuelID);

        fuel_choose= radioButton_fuel.getText().toString();

       // Toast.makeText(this, "You selected :"+fuel_choose, Toast.LENGTH_SHORT).show();

    }




    public void checkPaymentRB()
    {

        radioPaymentID=rg_payment.getCheckedRadioButtonId();

        radioButton_payment=findViewById(radioPaymentID);

        payment_Method = radioButton_payment.getText().toString();

      //  Toast.makeText(this, "You selected :"+payment_Method, Toast.LENGTH_SHORT).show();

    }









}
