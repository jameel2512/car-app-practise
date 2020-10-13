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
import com.example.jameel.everica.VendorApp.Helpers.CurrentRide_ModalClass_Helper_VendorApp;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by jameel on 4/16/2020.
 */

public class CurrentRide_ModalClass_Adapter extends RecyclerView.Adapter<CurrentRide_ModalClass_Adapter.CurrentRide_ViewHolder> {


    Context context;
    List<CurrentRide_ModalClass_Helper_VendorApp> listitems;

    int[] images_Car;




    public CurrentRide_ModalClass_Adapter(Context context, List<CurrentRide_ModalClass_Helper_VendorApp> listitems) {
        this.context = context;
        this.listitems = listitems;
    }

    @NonNull
    @Override
    public CurrentRide_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout_allrideshistory_vendorapp,parent,false);

        return new CurrentRide_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentRide_ViewHolder holder, int position) {



        int resourse_image=listitems.get(position).getImage_car();

        String text2=listitems.get(position).getField_2();
        String text3=listitems.get(position).getField_3();
        String text4=listitems.get(position).getField_4();
        String text5=listitems.get(position).getField_5();
        String text6=listitems.get(position).getField_6();

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
        return listitems.size();
    }







    public class CurrentRide_ViewHolder extends RecyclerView.ViewHolder{


        TextView textView_field_2,textView_field_3,textView_field_4,
                     textView_field_5,textView_field_6;
        ImageView imageView_car;

        public CurrentRide_ViewHolder(View itemView) {
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
