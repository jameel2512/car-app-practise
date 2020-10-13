package com.example.jameel.everica.VendorApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jameel.everica.R;
import com.example.jameel.everica.VendorApp.Adapters.CurrentRide_ModalClass_Adapter;
import com.example.jameel.everica.VendorApp.Helpers.CurrentRide_ModalClass_Helper_VendorApp;

import java.util.ArrayList;
import java.util.List;

public class CurrentRideActivity_VendorApp extends AppCompatActivity {


    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView_currentRide;

    List<CurrentRide_ModalClass_Helper_VendorApp> list_items_currentRide;

    CurrentRide_ModalClass_Adapter currentRide_Adapter;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_ride__vendor_app);





        recyclerView_currentRide=(RecyclerView) findViewById(R.id.id_currentride_recyclerview);



        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_currentRide.setLayoutManager(linearLayoutManager);

        list_items_currentRide=new ArrayList<>();

        list_items_currentRide.add(new CurrentRide_ModalClass_Helper_VendorApp(R.drawable.car1,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_currentRide.add(new CurrentRide_ModalClass_Helper_VendorApp(R.drawable.car3,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_currentRide.add(new CurrentRide_ModalClass_Helper_VendorApp(R.drawable.car4,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_currentRide.add(new CurrentRide_ModalClass_Helper_VendorApp(R.drawable.car5,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_currentRide.add(new CurrentRide_ModalClass_Helper_VendorApp(R.drawable.car6,"field 2","field 3",
                "field 4","field 5","field 6"));



        currentRide_Adapter=new CurrentRide_ModalClass_Adapter(this,list_items_currentRide);
        recyclerView_currentRide.setAdapter(currentRide_Adapter);
        currentRide_Adapter.notifyDataSetChanged();




    }



}
