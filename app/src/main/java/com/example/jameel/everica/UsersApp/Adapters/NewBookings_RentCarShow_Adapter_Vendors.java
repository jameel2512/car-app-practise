package com.example.jameel.everica.UsersApp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.TripFair_And_All_Information_UsersApp;
import com.example.jameel.everica.VendorApp.Helpers.NewBooking_RentCarsShow_Helper_VendorApp;

import java.util.List;

/**
 * Created by jameel on 4/7/2020.
 */

public class NewBookings_RentCarShow_Adapter_Vendors
        extends RecyclerView.Adapter<NewBookings_RentCarShow_Adapter_Vendors.RentCarViewHolder> {


   // public onItemClickListener listener;

    private Context context;

    private List<NewBooking_RentCarsShow_Helper_VendorApp> list_cars;

   // private List<String> list_cars;


    public NewBookings_RentCarShow_Adapter_Vendors(Context context, List<NewBooking_RentCarsShow_Helper_VendorApp> list_cars) {
        this.context = context;
        this.list_cars = list_cars;
    }



    @NonNull
    @Override
    public RentCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v=LayoutInflater.from(context).inflate(R.layout.itmes_layout_rentcar_usersapp,parent,false);
        return new RentCarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RentCarViewHolder holder, final int position) {

       //String current_car=list_cars.get(position);

       final NewBooking_RentCarsShow_Helper_VendorApp current_car = list_cars.get(position);

       // getData();

        holder.textView_carmodal_name.setText(current_car.getRc_car_modalname());
        holder.textView_carmodal_no.setText(current_car.getRc_car_modalno());
        holder.textView_carmodal_price.setText(current_car.getRc_car_price()+" Rs");
        holder.textView_carmodal_type.setText(current_car.getRc_cartype());
        holder.textView_carmodal_condition.setText(current_car.getRc_car_condition());

       // String id=current_car.getRc_car_userid();


        // holder.button_booknow.setText("Book Now");



        holder.relativeLayout_Rent_Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(context, TripFair_And_All_Information_UsersApp.class);
                intent.putExtra("carname",current_car.getRc_car_modalname());
                intent.putExtra("carno",current_car.getRc_car_modalno());
                intent.putExtra("carcategory",current_car.getRc_cartype());
                intent.putExtra("carprice",current_car.getRc_car_price());
                intent.putExtra("carcondition",current_car.getRc_car_condition());
                intent.putExtra("carownerid",current_car.getCar_owner_ID());

                context.startActivity(intent);
            }
        });





    }



    @Override
    public int getItemCount() {
        return list_cars.size();
    }




    public class RentCarViewHolder extends RecyclerView.ViewHolder{

        public TextView textView_carmodal_name,textView_carmodal_no,textView_carmodal_price,
                textView_carmodal_type,textView_carmodal_condition;
        Button button_booknow;


        RelativeLayout relativeLayout_Rent_Car;
       // public ImageView imageView_car;

        public RentCarViewHolder(View itemView) {
            super(itemView);

            textView_carmodal_name=itemView.findViewById(R.id.id_rc_itmes_carmodal_name);
            textView_carmodal_no=itemView.findViewById(R.id.id_rc_itmes_carmodal_no);
            textView_carmodal_type=itemView.findViewById(R.id.id_rc_itmes_car_type);
            textView_carmodal_condition=itemView.findViewById(R.id.id_rc_itmes_car_codition);
            textView_carmodal_price=itemView.findViewById(R.id.id_rc_itmes_carprice);
            button_booknow=itemView.findViewById(R.id.id_rc_items_booknow_btn);


            relativeLayout_Rent_Car=itemView.findViewById(R.id.relativelayout_rent_Car);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    int position=getAdapterPosition();
//
//                    if (position!=RecyclerView.NO_POSITION &&  listener!= null )
//                    {
//                        listener.onItemClick();
//                    }
//
//                }
//            });


           // imageView_car=itemView.findViewById(R.id.id_rc_itmes_carimage);

        }




    }


//
//
//    public interface onItemClickListener{
//
//        void onItemClick(DataSnapshot dataSnapshot,int position);
//       // void onItemClick(View view,int position);
//    }
//
//    public  void setOnItemClickListener(onItemClickListener listener){
//        this.listener=listener;
//    }
//
//








    /*

    private void getData(){

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
       DatabaseReference databaseReference_Vendors = FirebaseDatabase.getInstance().getReference("Vendors");


        databaseReference_Vendors.child("Rent Car Information")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                            String carmodal_name = dataSnapshot1.child("rc_car_modalname").getValue().toString();
                            String carmodal_no = dataSnapshot1.child("rc_car_modalno").getValue().toString();
                            String car_type = dataSnapshot1.child("rc_cartype").getValue().toString();
                            String car_condition = dataSnapshot1.child("rc_car_condition").getValue().toString();
                            String car_price_perHour = dataSnapshot1.child("rc_car_price").getValue().toString();


//                            textView_carmodal_name.setText(carmodal_name);
//                            holder.textView_carmodal_no.setText(carmodal_no);
//                            holder.textView_carmodal_price.setText(car_price_perHour);
//                            holder.textView_carmodal_condition.setText(car_condition);
//                            holder.textView_carmodal_type.setText(car_type);

                            Toast.makeText(context, "Retrieve all data" , Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Toast.makeText(context, "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                });

    }

*/



}



