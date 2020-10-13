package com.example.jameel.everica.UsersApp.Fragments;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Adapters.NewBookings_RentCarShow_Adapter_Vendors;
import com.example.jameel.everica.VendorApp.Helpers.NewBooking_RentCarsShow_Helper_VendorApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jameel on 4/9/2020.
 */

public class Fragment_RentCar extends Fragment {


    public ImageView imageView_rentcar;
    public TextView textView_carmodal_name, textView_carmodal_no, textView_cartype, textView_carcondition,
            textView_carprice_perhour, textView_rentcar_head;
    public Button button_addrentCar;

    private Button button_Booknow;


    private Uri filepathUri;
    private Bitmap bitmap;

    private int IMAGE_PICK_CODE;

    private DatabaseReference databaseReference_Vendors;

    public FirebaseAuth firebaseAuth;


    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    private RecyclerView recyclerView;
    private View v;
    private NewBookings_RentCarShow_Adapter_Vendors adapter_RentCar;

    //public List<RentCarsShow_ModalClass_Helper_VendorApp> list_of_rentCar;

    public List<NewBooking_RentCarsShow_Helper_VendorApp> list_of_rentCar;

    public View rentCar_view;

//    private Spinner spinner_car_Category;
//    private String[] spinner_car_Category_List={"Low","Economy","Go","Business"};
//    private ArrayAdapter<String> car_category_adapter;
//    String item_car_Category;
//

    private RadioGroup radioGroup_car_Category;
    private RadioButton radioButton_low, radioButton_economy, radioButton_go, radioButton_business;
    private int radioCarCategoryID;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rentCar_view = inflater.inflate(R.layout.fragment_rentcar, container, false);


        // spinner_car_Category=(Spinner)rentCar_view.findViewById(R.id.id_rentcar_car_categories);

        radioButton_business = (RadioButton) rentCar_view.findViewById(R.id.id_rc_rb_business);
        radioButton_go = (RadioButton) rentCar_view.findViewById(R.id.id_rc_rb_go);
        radioButton_economy = (RadioButton) rentCar_view.findViewById(R.id.id_rc_rb_economy);
        radioButton_low = (RadioButton) rentCar_view.findViewById(R.id.id_rc_rb_low);

        radioGroup_car_Category = (RadioGroup) rentCar_view.findViewById(R.id.id_rc_rg_car_categoty);


        textView_rentcar_head = (TextView) rentCar_view.findViewById(R.id.id_rentcar_texthead);


        recyclerView = (RecyclerView) rentCar_view.findViewById(R.id.id_rentacar_recyvlerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()
                , LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        list_of_rentCar = new ArrayList<>();


//
//        try {
//
//            car_category_adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,spinner_car_Category_List);
//            spinner_car_Category.setAdapter(car_category_adapter);
//
//
//            // textView_rentcar_head.setText("Rent A Car "+spinner_car_Category+" Category");
//
//        }catch (Exception e)
//        {
//            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//        }


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference_Vendors = FirebaseDatabase.getInstance().getReference("Vendors");


        return rentCar_view;

    }


    ////////////working code fine///////////////////


    @Override
    public void onStart() {
        super.onStart();




        try
        {

        radioGroup_car_Category.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int position) {


                switch (position) {


                    case R.id.id_rc_rb_low:

                        try {

                            checkCarCategoryRB();

                        }catch (Exception e)
                        {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        databaseReference_Vendors.child("Rent Car Information")
                                //.child(item_car_Category)
                                .child("Low")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {


                                        list_of_rentCar.clear();


                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
                                                    dataSnapshot1.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                                            list_of_rentCar.add(rentCarsShow_helper);
                                        }

                                        adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
                                        recyclerView.setAdapter(adapter_RentCar);

                                         adapter_RentCar.notifyDataSetChanged();


                                        Toast.makeText(getContext(), "displaying all data", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                        Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                        break;


                    case R.id.id_rc_rb_economy:

                        try {

                            checkCarCategoryRB();

                        }catch (Exception e)
                        {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        databaseReference_Vendors.child("Rent Car Information")
                                //.child(item_car_Category)
                                .child("Economy")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {


                                        list_of_rentCar.clear();

                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
                                                    dataSnapshot1.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                                            list_of_rentCar.add(rentCarsShow_helper);
                                        }

                                        adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
                                        recyclerView.setAdapter(adapter_RentCar);

                                        adapter_RentCar.notifyDataSetChanged();

                                        Toast.makeText(getContext(), "displaying all data", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                        Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                        break;


                    case R.id.id_rc_rb_go:


                        try {

                            checkCarCategoryRB();

                        }catch (Exception e)
                        {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }



                        databaseReference_Vendors.child("Rent Car Information")
                                //.child(item_car_Category)
                                .child("Go")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        list_of_rentCar.clear();


                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
                                                    dataSnapshot1.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                                            list_of_rentCar.add(rentCarsShow_helper);
                                        }

                                        adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
                                        recyclerView.setAdapter(adapter_RentCar);

                                        adapter_RentCar.notifyDataSetChanged();

                                        Toast.makeText(getContext(), "displaying all data", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                        Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                        break;


                    case R.id.id_rc_rb_business:


                        try {

                            checkCarCategoryRB();

                        }catch (Exception e)
                        {
                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                        databaseReference_Vendors.child("Rent Car Information")
                                //.child(item_car_Category)
                                .child("Business")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        list_of_rentCar.clear();

                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
                                                    dataSnapshot1.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                                            list_of_rentCar.add(rentCarsShow_helper);
                                        }

                                        adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
                                        recyclerView.setAdapter(adapter_RentCar);

                                        adapter_RentCar.notifyDataSetChanged();

                                        Toast.makeText(getContext(), "displaying all data", Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                        Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });

                        break;

                }


            }
        });

        }catch (Exception e)
        {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }



//        item_car_Category =spinner_car_Category.getSelectedItem().toString();
//
//        Toast.makeText(getContext(), "clicked: "+item_car_Category, Toast.LENGTH_SHORT).show();
//


    }


    public void checkCarCategoryRB() {

        radioCarCategoryID = radioGroup_car_Category.getCheckedRadioButtonId();

        RadioButton radioButton_carcategoty = (RadioButton)rentCar_view.findViewById(radioCarCategoryID);

        String car_Category = radioButton_carcategoty.getText().toString();

        textView_rentcar_head.setText("Rent A Car "+car_Category+" Category");

         Toast.makeText(getContext(), "You selected :"+car_Category, Toast.LENGTH_SHORT).show();

    }









}

        //  String catagory=spinner_car_Category.getSelectedItem().toString();


        /*



        spinner_car_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



                if (position==0)
                {

                }


                 else if(position==1)
                {
                    databaseReference_Vendors.child("Rent Car Information")
                            //.child(item_car_Category)
                            .child("Economy")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                        NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
                                                dataSnapshot1.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                                        list_of_rentCar.add(rentCarsShow_helper);
                                    }


//                        NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
//                                dataSnapshot.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);
//
//                        list_of_rentCar.add(rentCarsShow_helper);


                                    adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
                                    recyclerView.setAdapter(adapter_RentCar);

                                    Toast.makeText(getContext(), "displaying all data" , Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                }

                else if(position==2)
                {
                    databaseReference_Vendors.child("Rent Car Information")
                            //.child(item_car_Category)
                            .child("Go")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                        NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
                                                dataSnapshot1.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                                        list_of_rentCar.add(rentCarsShow_helper);
                                    }


//                        NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
//                                dataSnapshot.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);
//
//                        list_of_rentCar.add(rentCarsShow_helper);


                                    adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
                                    recyclerView.setAdapter(adapter_RentCar);

                                    Toast.makeText(getContext(), "displaying all data" , Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                }


               else
                {
                    databaseReference_Vendors.child("Rent Car Information")
                            //.child(item_car_Category)
                            .child("Business")
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                        NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
                                                dataSnapshot1.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                                        list_of_rentCar.add(rentCarsShow_helper);
                                    }


//                        NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_helper =
//                                dataSnapshot.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);
//
//                        list_of_rentCar.add(rentCarsShow_helper);


                                    adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
                                    recyclerView.setAdapter(adapter_RentCar);

                                    Toast.makeText(getContext(), "displaying all data" , Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                    Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                }







    }



        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });





*/











        /*

        adapter_RentCar.setOnItemClickListener(new NewBookings_RentCarShow_Adapter_Vendors.onItemClickListener() {
            @Override
            public void onItemClick(DataSnapshot dataSnapshot, int position) {

                NewBooking_RentCarsShow_Helper_VendorApp rentCarShow_helper  =
                        dataSnapshot.getValue(NewBooking_RentCarsShow_Helper_VendorApp.class);

                String vendor_Car_Holder_ID=rentCarShow_helper.getRc_car_userid();

                String vendor_Car_Holder_CarmodalNo=rentCarShow_helper.getRc_car_modalno();
                String vendor_Car_Holder_CarmodalName=rentCarShow_helper.getRc_car_modalname();
                String vendor_Car_Holder_CarmodalCondition=rentCarShow_helper.getRc_car_condition();
                String vendor_Car_Holder_CarmodalPrice=rentCarShow_helper.getRc_car_price();
                String vendor_Car_Holder_CarmodalType=rentCarShow_helper.getRc_cartype();

                Intent intent=new Intent(getContext(), TripFair_And_All_Information_UsersApp.class);
                intent.putExtra("carname",vendor_Car_Holder_CarmodalName);
                intent.putExtra("carno",vendor_Car_Holder_CarmodalNo);
                intent.putExtra("cartype",vendor_Car_Holder_CarmodalType);
                intent.putExtra("carprice",vendor_Car_Holder_CarmodalPrice);
                intent.putExtra("carcondition",vendor_Car_Holder_CarmodalCondition);
                intent.putExtra("carid",vendor_Car_Holder_ID);

            }


        });


*/
















/*

    @Override
    public void onStart() {
        super.onStart();



        ////////////working code but showing last value just///////////////////


        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions
                .Builder<NewBooking_RentCarsShow_Helper_VendorApp>()

                .setQuery(databaseReference_Vendors.child("Rent Car Information")
                        , NewBooking_RentCarsShow_Helper_VendorApp.class)
                .build();

//        .setQuery(databaseReference_Vendors.child(firebaseAuth.getCurrentUser().getUid())
//                        .child("Rent Car Information"), RentCarsShow_ModalClass_Helper_VendorApp.class)
//                .build();


        FirebaseRecyclerAdapter<NewBooking_RentCarsShow_Helper_VendorApp, RentCarViewHolder> adapter_RentCar =
                new FirebaseRecyclerAdapter<NewBooking_RentCarsShow_Helper_VendorApp, RentCarViewHolder>(options) {

                    @Override
                    protected void onBindViewHolder(@NonNull final RentCarViewHolder holder, int position,
                                                    @NonNull NewBooking_RentCarsShow_Helper_VendorApp model) {

                        String userid=getRef(position).getKey();


//                        holder.textView_carmodal_name.setText(model.getRc_car_modalname());
//                        holder.textView_carmodal_no.setText(model.getRc_car_modalno());
//                        holder.textView_carmodal_condition.setText(model.getRc_car_condition());
//                        holder.textView_carmodal_price.setText(model.getRc_car_price());
//                        holder.textView_carmodal_type.setText(model.getRc_cartype());




                        databaseReference_Vendors
                                .child("Rent Car Information")

                               // .child(model.getRc_car_userid())

                                //  .child(userid)

                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                            String carmodal_name = dataSnapshot1.child("rc_car_modalname").getValue().toString();
                                            String carmodal_no = dataSnapshot1.child("rc_car_modalno").getValue().toString();
                                            String car_type = dataSnapshot1.child("rc_cartype").getValue().toString();
                                            String car_condition = dataSnapshot1.child("rc_car_condition").getValue().toString();
                                            String car_price_perHour = dataSnapshot1.child("rc_car_price").getValue().toString();

//                                        String carmodal_name = dataSnapshot.child("rc_car_modalname").getValue().toString();
//                                        String carmodal_no = dataSnapshot.child("rc_car_modalno").getValue().toString();
//                                        String car_type = dataSnapshot.child("rc_cartype").getValue().toString();
//                                        String car_condition = dataSnapshot.child("rc_car_condition").getValue().toString();
//                                        String car_price_perHour = dataSnapshot.child("rc_car_price").getValue().toString();
//


                                             holder.textView_carmodal_name.setText(carmodal_name);
                                            holder.textView_carmodal_no.setText(carmodal_no);
                                            holder.textView_carmodal_price.setText(car_price_perHour);
                                            holder.textView_carmodal_condition.setText(car_condition);
                                            holder.textView_carmodal_type.setText(car_type);


                                            Toast.makeText(getContext(), "ALL Rent A Car Data Retrieved ", Toast.LENGTH_SHORT).show();
                                      }



//                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                                    RentCarsShow_ModalClass_Helper_VendorApp rentCarsShow_helper =
//                                            dataSnapshot1.getValue(RentCarsShow_ModalClass_Helper_VendorApp.class);
//
//                                    list_of_rentCar.add(rentCarsShow_helper);
//                                }
//
//                                adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
//
//                                recyclerView.setAdapter(adapter_RentCar);


                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                        Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                });









                    }


                    @NonNull
                    @Override
                    public RentCarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itmes_layout_rentcar_usersapp, parent, false);
                        RentCarViewHolder viewHolder = new RentCarViewHolder(view);
                        return viewHolder;
                    }
                };


        recyclerView.setAdapter(adapter_RentCar);
        adapter_RentCar.startListening();


}













    public static class RentCarViewHolder extends RecyclerView.ViewHolder {

        public TextView textView_carmodal_name, textView_carmodal_no, textView_carmodal_price,
                textView_carmodal_type, textView_carmodal_condition;
        // public ImageView imageView_car;


        public RentCarViewHolder(View itemView) {
            super(itemView);

            textView_carmodal_name = itemView.findViewById(R.id.id_rc_itmes_carmodal_name);
            textView_carmodal_no = itemView.findViewById(R.id.id_rc_itmes_carmodal_no);
            textView_carmodal_type = itemView.findViewById(R.id.id_rc_itmes_car_type);
            textView_carmodal_condition = itemView.findViewById(R.id.id_rc_itmes_car_codition);
            textView_carmodal_price = itemView.findViewById(R.id.id_rc_itmes_carprice);

            // imageView_car=itemView.findViewById(R.id.id_rc_itmes_carimage);


        }

    }




*/




























//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        this.v = view;
//
//        button_addrentCar = (Button) v.findViewById(R.id.id_rc_addButton);
//
//        textView_carcondition = (TextView) v.findViewById(R.id.id_rc_carcondition);
//        textView_carmodal_name = (TextView) v.findViewById(R.id.id_rc_carmodalname);
//        textView_carmodal_no = (TextView) v.findViewById(R.id.id_rc_carmodalno);
//        textView_carprice_perhour = (TextView) v.findViewById(R.id.id_rc_carprice_hourly);
//        textView_cartype = (TextView) v.findViewById(R.id.id_rc_cartype);
//
//        // imageView_rentcar=(ImageView) v.findViewById(R.id.id_rc_carimage);
//
//
//        //  storageReference = FirebaseStorage.getInstance().getReference("Users").child(firebaseAuth.getCurrentUser().getUid());
//
//
//        databaseReference_Vendors.child(firebaseAuth.getCurrentUser().getUid())
//                .child("Rent Car Information")
//                .addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
//
//                            RentCarsShow_ModalClass_Helper_VendorApp rentCarsShow_helper =
//                                    dataSnapshot1.getValue(RentCarsShow_ModalClass_Helper_VendorApp.class);
//
//                            list_of_rentCar.add(rentCarsShow_helper);
//                        }
//
//                        adapter_RentCar = new NewBookings_RentCarShow_Adapter_Vendors(getContext(), list_of_rentCar);
//
//                        recyclerView.setAdapter(adapter_RentCar);
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                        Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//
//    }



//
//        imageView_rentcar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//               CropImage.activity().start((Activity) getContext());
//
//            }
//        });











//    private String getFileExtension(Uri imageUri){
//
//        ContentResolver contentResolver=getContentResolver();
//        MimeTypeMap mimeTypeMap= MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));
//
//    }
//
//
//
////
//    private void UploadCar_Information() {
//
//       // final String nametheme = editText_themeName.getText().toString().trim();
//
//        if (nametheme != null) {
//
//            final ProgressDialog progressDialog = new ProgressDialog(getContext());
//            progressDialog.setTitle("Uploading");
//            progressDialog.setMessage("Please Wait for a while....");
//            progressDialog.setCanceledOnTouchOutside(true);
//            progressDialog.show();
//
//
//            final StorageReference storageReference1 = storageReference
//                    .child("Profile Theme Backgroound")
//                    .child(nametheme)
//                    .child(System.currentTimeMillis() + "." + getFileExtension(filepathUri));
//
//            storageReference1.putFile(filepathUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            progressDialog.setProgress(0);
//
//                            RentCarsShow_ModalClass_Helper_VendorApp rentCarsShow_helper =
//                                   new RentCarsShow_ModalClass_Helper_VendorApp();
//
//
//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                }
//                            }, 5000);
//
//                            //String uploadId=databaseReference_Vendors.push().getKey();
//
//
//                            databaseReference_Vendors.child(firebaseAuth.getCurrentUser().getUid())
//                                    .child("Profile Theme Backgroound")
//                                    .child(nametheme)
//                                    //.child(uploadid)
//                                    .setValue(card_profileBackgroundTheme_helper);
//
//                            progressDialog.dismiss();
//
//                            Toast.makeText(ShowthemesActivity.this, "Image stored", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//
//                    Toast.makeText(ShowthemesActivity.this, "Image Not stored", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressDialog.setProgress((int) progress);
//                }
//            });
//
//
//        }else{
//
//            Toast.makeText(this, "Enter Theme Name", Toast.LENGTH_SHORT).show();
//        }
//
//
//    }
//



