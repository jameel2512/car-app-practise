package com.example.jameel.everica.VendorApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jameel.everica.R;
import com.example.jameel.everica.VendorApp.Adapters.AllRideHistory_ModalClass_Adapter;
import com.example.jameel.everica.VendorApp.Helpers.AllRideHistory_ModalClass_Helper_VendorsApp;

import java.util.ArrayList;
import java.util.List;

public class AllRideHistoryActivity_VendorApp extends AppCompatActivity {


    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView_allRide;

    private int[] images_Car={R.drawable.car1,R.drawable.car3,
            R.drawable.car4,R.drawable.car5,R.drawable.car6};


    List<AllRideHistory_ModalClass_Helper_VendorsApp> list_items_allRide;

    AllRideHistory_ModalClass_Adapter allRide_Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ride_history_vendorapp);



        recyclerView_allRide=(RecyclerView) findViewById(R.id.id_allrides_recylerview);



        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_allRide.setLayoutManager(linearLayoutManager);

        list_items_allRide=new ArrayList<>();

        list_items_allRide.add(new AllRideHistory_ModalClass_Helper_VendorsApp(R.drawable.car1,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_allRide.add(new AllRideHistory_ModalClass_Helper_VendorsApp(R.drawable.car3,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_allRide.add(new AllRideHistory_ModalClass_Helper_VendorsApp(R.drawable.car4,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_allRide.add(new AllRideHistory_ModalClass_Helper_VendorsApp(R.drawable.car5,"field 2","field 3",
                "field 4","field 5","field 6"));

        list_items_allRide.add(new AllRideHistory_ModalClass_Helper_VendorsApp(R.drawable.car6,"field 2","field 3",
                "field 4","field 5","field 6"));



        allRide_Adapter=new AllRideHistory_ModalClass_Adapter(this,list_items_allRide);
         recyclerView_allRide.setAdapter(allRide_Adapter);
         allRide_Adapter.notifyDataSetChanged();




    }



}
