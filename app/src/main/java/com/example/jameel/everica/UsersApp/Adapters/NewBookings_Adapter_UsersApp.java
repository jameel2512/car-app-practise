package com.example.jameel.everica.UsersApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Helpers.NewBooking_RentCar_Information_Helper_UsersApp;
import com.example.jameel.everica.UsersApp.NewBooking_Details_Show_UsersApp;

import java.util.List;

/**
 * Created by jameel on 5/9/2020.
 */

public class NewBookings_Adapter_UsersApp extends RecyclerView.Adapter<NewBookings_Adapter_UsersApp.My_Bookings_View_Holder>{

    private Context context;

    private List<NewBooking_RentCar_Information_Helper_UsersApp> list_bookings;


    public NewBookings_Adapter_UsersApp(Context context, List<NewBooking_RentCar_Information_Helper_UsersApp> list_bookings) {
        this.context = context;
        this.list_bookings = list_bookings;
    }

    @NonNull
    @Override
    public My_Bookings_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.items_layout_newbooking_usersapp,parent,false);
        return new My_Bookings_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_Bookings_View_Holder holder, final int position) {

        final NewBooking_RentCar_Information_Helper_UsersApp my_Current_booking = list_bookings.get(position);

        holder.total_fare_tv.setText(my_Current_booking.getTotal_Fare());
        holder.pickup_Location_tv.setText(my_Current_booking.getPickup_Location());

        holder.customer_Name_No_tv.setText(my_Current_booking.getCustomer_Name()
                +"\n"+my_Current_booking.getCustomer_No());

        holder.starting_Date_time_tv.setText(my_Current_booking.getStartingTrip_Date()
                +" "+my_Current_booking.getStartingTrip_Time());

        holder.carmodal_name_no_tv.setText(my_Current_booking.getCarModal_Name()
                +" "+my_Current_booking.getCarModal_No());

        holder.relativeLayout_mybookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,
                        NewBooking_Details_Show_UsersApp.class);

                intent.putExtra("car_owner_id",my_Current_booking.getCar_owner_ID());
                intent.putExtra("customer_id",my_Current_booking.getCustomer_ID());

                intent.putExtra("customer_name",my_Current_booking.getCustomer_Name());
                intent.putExtra("customer_no",my_Current_booking.getCustomer_No());

                intent.putExtra("carmodal_name",my_Current_booking.getCarModal_Name());
                intent.putExtra("carmodal_no",my_Current_booking.getCarModal_No());

                intent.putExtra("pickup_location",my_Current_booking.getPickup_Location());
                intent.putExtra("destination_location",my_Current_booking.getDestination_Location());
                intent.putExtra("total_fare",my_Current_booking.getTotal_Fare());
                intent.putExtra("booking_date",my_Current_booking.getBooking_Date());

                intent.putExtra("starting_date",my_Current_booking.getStartingTrip_Date());
                intent.putExtra("starting_time",my_Current_booking.getStartingTrip_Time());


                intent.putExtra("ending_date",my_Current_booking.getEndingTrip_Date());
                intent.putExtra("ending_time",my_Current_booking.getEndingTrip_Time());

                intent.putExtra("fuel_choose",my_Current_booking.getFuel_Choose());
                intent.putExtra("payment_method_choose",my_Current_booking.getPayment_Method());
                intent.putExtra("driver_choose",my_Current_booking.getDriver_Choose());
                intent.putExtra("hours",my_Current_booking.getHoursTrip_CarRent());
                intent.putExtra("car_category",my_Current_booking.getCar_Category());
                intent.putExtra("car_price_per_hour",my_Current_booking.getCar_Price_Per_Hr());

                intent.putExtra("booking_date",my_Current_booking.getBooking_Date());
                intent.putExtra("booking_time",my_Current_booking.getBooking_Time());



                //  intent.putExtra("my_booking_position",position);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_bookings.size();
    }

    public class My_Bookings_View_Holder extends RecyclerView.ViewHolder{

        public TextView carmodal_name_no_tv,customer_Name_No_tv,pickup_Location_tv
                ,starting_Date_time_tv,total_fare_tv;

        RelativeLayout relativeLayout_mybookings;


        public My_Bookings_View_Holder(View itemView) {
            super(itemView);

            carmodal_name_no_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_carmodal_Name_No);
            customer_Name_No_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_customer_name_no);
            total_fare_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_total_fare);
            pickup_Location_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_pickup_location);
            starting_Date_time_tv= (TextView) itemView.findViewById(R.id.id_my_bookings_items_starting_Date_Time);

            relativeLayout_mybookings=itemView.findViewById(R.id.relativelayout_mybookings_usersapp);

        }
    }

}
