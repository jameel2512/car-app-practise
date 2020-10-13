package com.example.jameel.everica.VendorApp.Adapters;

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
import com.example.jameel.everica.VendorApp.NewBooking_Details_Show_VendorsApp;

import java.util.List;

/**
 * Created by jameel on 5/9/2020.
 */

public class NewBookings_Adapter_VendorApp extends
        RecyclerView.Adapter<NewBookings_Adapter_VendorApp.My_Bookings_To_Be_Complete_View_Holder>
{

    private Context context;
    private List<NewBooking_RentCar_Information_Helper_UsersApp> list_bookings_To_Be_Complete;


    public NewBookings_Adapter_VendorApp(Context context,
                                         List<NewBooking_RentCar_Information_Helper_UsersApp>
                                                                list_bookings_To_Be_Complete) {
        this.context = context;
        this.list_bookings_To_Be_Complete = list_bookings_To_Be_Complete;
    }


    @NonNull
    @Override
    public My_Bookings_To_Be_Complete_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate
                (R.layout.items_layout_newbooking_vendorapp,parent,false);
        return new My_Bookings_To_Be_Complete_View_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_Bookings_To_Be_Complete_View_Holder holder, int position) {

        final NewBooking_RentCar_Information_Helper_UsersApp my_Current_booking_to_be_complte_helper =
                list_bookings_To_Be_Complete.get(position);

        holder.total_fare_tv.setText(my_Current_booking_to_be_complte_helper.getTotal_Fare());
        holder.pickup_Location_tv.setText(my_Current_booking_to_be_complte_helper.getPickup_Location());

        holder.customer_Name_No_tv.setText(my_Current_booking_to_be_complte_helper.getCustomer_Name()
                +" \n "+my_Current_booking_to_be_complte_helper.getCustomer_No());

        holder.starting_Date_time_tv.setText(my_Current_booking_to_be_complte_helper.getStartingTrip_Date()
        +" "+my_Current_booking_to_be_complte_helper.getStartingTrip_Time());

        holder.carmodal_name_no_tv.setText(my_Current_booking_to_be_complte_helper.getCarModal_Name()
        +" "+my_Current_booking_to_be_complte_helper.getCarModal_No());



        holder.relativeLayout_mybookings_ToBeComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context,
                        NewBooking_Details_Show_VendorsApp.class);

                intent.putExtra("car_owner_id",my_Current_booking_to_be_complte_helper.getCar_owner_ID());
                intent.putExtra("customer_id",my_Current_booking_to_be_complte_helper.getCustomer_ID());
                intent.putExtra("customer_name",my_Current_booking_to_be_complte_helper.getCustomer_Name());
                intent.putExtra("customer_no",my_Current_booking_to_be_complte_helper.getCustomer_No());
                intent.putExtra("carmodal_name",my_Current_booking_to_be_complte_helper.getCarModal_Name());
                intent.putExtra("carmodal_no",my_Current_booking_to_be_complte_helper.getCarModal_No());
                intent.putExtra("pickup_location",my_Current_booking_to_be_complte_helper.getPickup_Location());
                intent.putExtra("destination_location",my_Current_booking_to_be_complte_helper.getDestination_Location());
                intent.putExtra("total_fare",my_Current_booking_to_be_complte_helper.getTotal_Fare());
                intent.putExtra("booking_date",my_Current_booking_to_be_complte_helper.getBooking_Date());
                intent.putExtra("starting_date",my_Current_booking_to_be_complte_helper.getStartingTrip_Date());
                intent.putExtra("starting_time",my_Current_booking_to_be_complte_helper.getStartingTrip_Time());
                intent.putExtra("ending_date",my_Current_booking_to_be_complte_helper.getEndingTrip_Date());
                intent.putExtra("ending_time",my_Current_booking_to_be_complte_helper.getEndingTrip_Time());
                intent.putExtra("fuel_choose",my_Current_booking_to_be_complte_helper.getFuel_Choose());
                intent.putExtra("payment_method_choose",my_Current_booking_to_be_complte_helper.getPayment_Method());
                intent.putExtra("driver_choose",my_Current_booking_to_be_complte_helper.getDriver_Choose());
                intent.putExtra("hours",my_Current_booking_to_be_complte_helper.getHoursTrip_CarRent());
                intent.putExtra("car_category",my_Current_booking_to_be_complte_helper.getCar_Category());
                intent.putExtra("car_price_per_hour",my_Current_booking_to_be_complte_helper.getCar_Price_Per_Hr());

                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list_bookings_To_Be_Complete.size();
    }

    public class My_Bookings_To_Be_Complete_View_Holder extends RecyclerView.ViewHolder{

        public TextView carmodal_name_no_tv,carmodal_no_tv,customer_Name_No_tv,customer_No_tv,pickup_Location_tv
                ,starting_Date_time_tv,starting_time_tv,total_fare_tv;

        RelativeLayout relativeLayout_mybookings_ToBeComplete;


        public My_Bookings_To_Be_Complete_View_Holder(View itemView) {
            super(itemView);

            carmodal_name_no_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_carmodal_Name_No);
            customer_Name_No_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_customer_name_no);
            total_fare_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_total_fare);
            pickup_Location_tv = (TextView)itemView.findViewById(R.id.id_my_bookings_items_pickup_location);
            starting_Date_time_tv= (TextView) itemView.findViewById(R.id.id_my_bookings_items_starting_Date_Time);


             relativeLayout_mybookings_ToBeComplete=itemView.findViewById(R.id.relativelayout_mybookings_vendorsapp);

        }
    }

}