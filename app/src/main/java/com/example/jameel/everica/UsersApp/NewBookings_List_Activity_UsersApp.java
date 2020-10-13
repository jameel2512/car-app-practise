package com.example.jameel.everica.UsersApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Adapters.NewBookings_Adapter_UsersApp;
import com.example.jameel.everica.UsersApp.Helpers.NewBooking_RentCar_Information_Helper_UsersApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewBookings_List_Activity_UsersApp extends AppCompatActivity {


    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView_my_bookings;

    List<NewBooking_RentCar_Information_Helper_UsersApp> list_items_my_bookings;

    NewBookings_Adapter_UsersApp my_bookings_Adapter;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbookings_listshow_users_app);



        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");


        recyclerView_my_bookings=(RecyclerView) findViewById(R.id.id_mybookings_recyclerview);


        recyclerView_my_bookings.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false);
        recyclerView_my_bookings.setLayoutManager(linearLayoutManager);


//        linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView_my_bookings.setLayoutManager(linearLayoutManager);


        list_items_my_bookings=new ArrayList<>();



                databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                .child("Customer Booking Trips Confirmed")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            NewBooking_RentCar_Information_Helper_UsersApp my_booking_helper
                                    = dataSnapshot1.getValue(NewBooking_RentCar_Information_Helper_UsersApp.class);

                                    list_items_my_bookings.add(my_booking_helper);
                        }

                        my_bookings_Adapter = new NewBookings_Adapter_UsersApp(NewBookings_List_Activity_UsersApp.this
                                , list_items_my_bookings);

                        recyclerView_my_bookings.setAdapter(my_bookings_Adapter);

                       // my_bookings_Adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Toast.makeText(NewBookings_List_Activity_UsersApp.this,
                                databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }
}
