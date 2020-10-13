package com.example.jameel.everica.VendorApp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jameel.everica.R;
import com.example.jameel.everica.VendorApp.Helpers.AllRideHistory_ModalClass_Helper_VendorsApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jameel on 4/16/2020.
 */

public class AllRideHistory_ModalClass_Adapter extends RecyclerView.Adapter<AllRideHistory_ModalClass_Adapter.AllRide_ViewHolder> {


    Context context;
    List<AllRideHistory_ModalClass_Helper_VendorsApp> list_all_Rides;

    int[] images_Car;




    public AllRideHistory_ModalClass_Adapter(Context context, List<AllRideHistory_ModalClass_Helper_VendorsApp> list_all_Rides) {
        this.context = context;
        this.list_all_Rides = list_all_Rides;
    }

    @NonNull
    @Override
    public AllRide_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout_allrideshistory_vendorapp,parent,false);

        return new AllRide_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllRide_ViewHolder holder, int position) {



        int resourse_image= list_all_Rides.get(position).getImage_car();

        String text2=list_all_Rides.get(position).getField_2();
        String text3=list_all_Rides.get(position).getField_3();
        String text4=list_all_Rides.get(position).getField_4();
        String text5=list_all_Rides.get(position).getField_5();
        String text6=list_all_Rides.get(position).getField_6();

        holder.setDatat(resourse_image,text2,text3,text4,text5,text6);





//        int imageCar_id=images_Car[position];
//
//        holder.textView_field_2.setText(listitems.get(position).getField_2());
//        holder.textView_field_3.setText(listitems.get(position).getField_3());
//        holder.textView_field_4.setText(listitems.get(position).getField_4());
//        holder.textView_field_5.setText(listitems.get(position).getField_5());
//        holder.textView_field_6.setText(listitems.get(position).getField_6());
//
//
//        Picasso.get()
//                .load(listitems.get(position).getImage_car())
//                .fit()
//                .into(holder.imageView_car);
//

    }

    @Override
    public int getItemCount() {
        return list_all_Rides.size();
    }







    public class AllRide_ViewHolder extends RecyclerView.ViewHolder{


        TextView textView_field_2,textView_field_3,textView_field_4,
                     textView_field_5,textView_field_6;
        ImageView imageView_car;

        public AllRide_ViewHolder(View itemView) {
            super(itemView);

            textView_field_2=(TextView) itemView.findViewById(R.id.id_allride_items_field_2);
            textView_field_3=(TextView) itemView.findViewById(R.id.id_allride_items_field_3);
            textView_field_4=(TextView) itemView.findViewById(R.id.id_allride_items_field_4);
            textView_field_5=(TextView) itemView.findViewById(R.id.id_allride_items_field_5);
            textView_field_6=(TextView) itemView.findViewById(R.id.id_allride_items_field_6);

           imageView_car=(CircleImageView)itemView.findViewById(R.id.id_allride_items_image);

        }

        private void setDatat(int imageRes,String text2,String text3,String text4,String text5,String text6)
        {
            imageView_car.setImageResource(imageRes);

            textView_field_2.setText(text2);
            textView_field_3.setText(text3);
            textView_field_4.setText(text4);
            textView_field_5.setText(text5);
            textView_field_6.setText(text6);

        }


    }


}
