package com.example.jameel.everica.VendorApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Helpers.NewBooking_RentCar_Information_Helper_UsersApp;
import com.example.jameel.everica.VendorApp.Adapters.NewBookings_Adapter_VendorApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewBooking_List_VendorApp extends AppCompatActivity {


    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView_my_bookings_to_be_complete;

    List<NewBooking_RentCar_Information_Helper_UsersApp> list_items_my_bookings_to_be_complete;

    NewBookings_Adapter_VendorApp my_bookings_to_be_complete_Adapter;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference_vendor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbooking_listshow_vendor_app);


        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference_vendor= FirebaseDatabase.getInstance().getReference("Vendors");


        recyclerView_my_bookings_to_be_complete=(RecyclerView) findViewById(R.id.id_my_bookings_to_be_complete_recyclerview);


        recyclerView_my_bookings_to_be_complete.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false);
        recyclerView_my_bookings_to_be_complete.setLayoutManager(linearLayoutManager);


        list_items_my_bookings_to_be_complete=new ArrayList<>();



        databaseReference_vendor.child(firebaseAuth.getCurrentUser().getUid())
                .child("Vendor Booking Trip To Be Complete")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            NewBooking_RentCar_Information_Helper_UsersApp my_booking_to_be_complete_helper
                                    = dataSnapshot1.getValue(NewBooking_RentCar_Information_Helper_UsersApp.class);

                            list_items_my_bookings_to_be_complete.add(my_booking_to_be_complete_helper);
                        }


                        my_bookings_to_be_complete_Adapter = new NewBookings_Adapter_VendorApp(
                                NewBooking_List_VendorApp.this
                                , list_items_my_bookings_to_be_complete);

                        recyclerView_my_bookings_to_be_complete.setAdapter(my_bookings_to_be_complete_Adapter);

                        // my_bookings_Adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Toast.makeText(NewBooking_List_VendorApp.this,
                                databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });











//
//        databaseReference_vendor
//                .child("Customer Booking Trips Confirmed")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
//                        {
//                            NewBooking_RentCar_Information_Helper_UsersApp my_booking_to_be_complete_helper
//                                    = dataSnapshot1.getValue(NewBooking_RentCar_Information_Helper_UsersApp.class);
//
//                            list_items_my_bookings_to_be_complete.add(my_booking_to_be_complete_helper);
//                        }
//
//
//                        my_bookings_to_be_complete_Adapter = new NewBookings_Adapter_VendorApp(
//                                NewBooking_List_VendorApp.this
//                                , list_items_my_bookings_to_be_complete);
//
//                        recyclerView_my_bookings_to_be_complete.setAdapter(my_bookings_to_be_complete_Adapter);
//
//                        // my_bookings_Adapter.notifyDataSetChanged();
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                        Toast.makeText(NewBooking_List_VendorApp.this,
//                                databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });






    }



}
