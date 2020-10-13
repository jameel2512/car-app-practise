package com.example.jameel.everica.VendorApp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.VendorApp.Helpers.NewBooking_RentCarsShow_Helper_VendorApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class Adding_RentCar_Information_VendorApp extends AppCompatActivity {

   // public ImageView imageView_rentcar;
    //private CircleImageView imageView_rentcar;

    public TextView textView_carmodal_name, textView_carmodal_no, textView_cartype, textView_carcondition,
            textView_carprice_perhour;
    public Button button_addrentCar;


//    public Uri filepathUri;
//    private Bitmap bitmap;
//
//    private int IMAGE_PICK_CODE;

    public DatabaseReference databaseReference_Vendors;
    public FirebaseAuth firebaseAuth;
    public StorageReference storageReference;


//    ProgressDialog progressDialog;
//
//    ProgressBar progressBar;


    // private  String carmodal_name,carmodal_no,carmodal_type,carmodal_condition,carmodal_price;

    private Spinner spinner_car_Category;
    private String[] spinner_car_Category_List={"Low","Economy","Go","Business"};
    private ArrayAdapter<String> car_category_adapter;
  //  private String item_car_Category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding__rent_car__information__vendor_app);


        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference_Vendors = FirebaseDatabase.getInstance().getReference("Vendors");

       //  storageReference = FirebaseStorage.getInstance().getReference("Vendors");


       // progressDialog= new ProgressDialog(Adding_RentCar_Information_VendorApp.this);






        textView_carcondition = (TextView) findViewById(R.id.id_adding_rc_carcondition);
        textView_carmodal_name = (TextView) findViewById(R.id.id_adding_rc_carmodalname);
        textView_carmodal_no = (TextView) findViewById(R.id.id_adding_rc_carmodalno);
        textView_carprice_perhour = (TextView) findViewById(R.id.id_adding_rc_carprice_hourly);

        spinner_car_Category= (Spinner) findViewById(R.id.id_adding_rc_car_category);

        button_addrentCar = (Button) findViewById(R.id.id_rc_addButton);


        //  textView_cartype = (TextView) findViewById(R.id.id_rc_cartype);

       //imageView_rentcar = (ImageView) findViewById(R.id.id_rc_carimage);

       //progressBar=(ProgressBar) findViewById(R.id.id_rc_progressbar);

        //  imageView_rentcar=(CircleImageView) findViewById(R.id.id_rc_carimage);






/*
        imageView_rentcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // CropImage.activity().start(Adding_RentCar_Information_VendorApp.this);


                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent1, IMAGE_PICK_CODE);

            }
        });

*/



        try {

            car_category_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    spinner_car_Category_List);
            spinner_car_Category.setAdapter(car_category_adapter);

        }catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }



        button_addrentCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //  Add_Rent_A_Car_withImage();

                Add_Rent_A_Car();

            }
        });




    }





    private void Add_Rent_A_Car() {


        try {

//
//                    final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading");
//            progressDialog.setMessage("Please Wait for a while....");
//            progressDialog.setCanceledOnTouchOutside(true);
//            progressDialog.show();



            String item_car_Category=spinner_car_Category.getSelectedItem().toString();

            String carOwnerID=firebaseAuth.getCurrentUser().getUid();

           // String userid =

            String carmodal_name = textView_carmodal_name.getText().toString();
            String carmodal_no = textView_carmodal_no.getText().toString();
            String carmodal_price = textView_carprice_perhour.getText().toString();
            //  String carmodal_type = textView_cartype.getText().toString();
            String carmodal_condition = textView_carcondition.getText().toString();

//            RentCarsShow_ModalClass_Helper_VendorApp rentCarsShow_helper =
//                    new RentCarsShow_ModalClass_Helper_VendorApp(carmodal_price, carmodal_name, carmodal_no
//                            , item_car_Category, carmodal_condition);


            NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_id_helper =
                    new NewBooking_RentCarsShow_Helper_VendorApp(carmodal_price, carmodal_name, carmodal_no
                            , item_car_Category, carmodal_condition,carOwnerID);


            databaseReference_Vendors
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child("Rent Car Information")
                    .child(item_car_Category)
                    .child(carmodal_name+" "+carmodal_no)
                    .setValue(rentCarsShow_id_helper)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(Adding_RentCar_Information_VendorApp.this,
                                        "Stored All Data", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Data Not Stored\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Data failed\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        }catch (Exception e)
        {
            Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Exception\n"+e.getMessage() + e.getMessage(), Toast.LENGTH_SHORT).show();

        }







        try {





            String carmodal_name = textView_carmodal_name.getText().toString();
            String carmodal_no = textView_carmodal_no.getText().toString();
            String carmodal_price = textView_carprice_perhour.getText().toString();
            // String carmodal_type = textView_cartype.getText().toString();
            String carmodal_condition = textView_carcondition.getText().toString();


            String item_car_Category=spinner_car_Category.getSelectedItem().toString();

            String carOwnerID=firebaseAuth.getCurrentUser().getUid();




            NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_id_helper =
                    new NewBooking_RentCarsShow_Helper_VendorApp(carmodal_price, carmodal_name, carmodal_no
                            , item_car_Category, carmodal_condition,carOwnerID);

            databaseReference_Vendors.
                    child("Rent Car Information")
                    .child(item_car_Category)
                    .child(carmodal_name+" "+carmodal_no+"      "+carOwnerID)
                    .setValue(rentCarsShow_id_helper)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Stored All Data", Toast.LENGTH_SHORT).show();

                                Intent intent=new Intent(Adding_RentCar_Information_VendorApp.this
                                        ,NewBooking_List_VendorApp.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Data Not Stored\n" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Data failed\n" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e)
        {
            Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Exception\n"+e.getMessage() + e.getMessage(), Toast.LENGTH_SHORT).show();

        }



    }











/*

    private String getFileExtension(Uri imageUri){

        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap= MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));

    }





    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null
                && data.getData() != null) {

            try {

                filepathUri = data.getData();

//                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepathUri);
//                imageView_rentcar.setImageBitmap(bitmap);

                 Picasso.get().load(filepathUri).into(imageView_rentcar);

            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }





    private void Add_Rent_A_Car_withImage() {

//        firebaseAuth = FirebaseAuth.getInstance();
//        databaseReference_Vendors = FirebaseDatabase.getInstance().getReference().child("Vendors");
//        storageReference = FirebaseStorage.getInstance().getReference().child("Vendors");



//            final ProgressDialog progressDialog = new ProgressDialog(this);
//            progressDialog.setTitle("Uploading");
//            progressDialog.setMessage("Please Wait for a while....");
//            progressDialog.setCanceledOnTouchOutside(true);
//            progressDialog.show();




        try {

            final String carmodal_name = textView_carmodal_name.getText().toString().trim();
            final String carmodal_no = textView_carmodal_no.getText().toString();
            final String carmodal_price = textView_carprice_perhour.getText().toString();
            final String carmodal_type = textView_cartype.getText().toString();
            final String carmodal_condition = textView_carcondition.getText().toString();





            storageReference
                    .child(firebaseAuth.getCurrentUser().getUid())
                    .child("Rent Car Information")
                    .child(carmodal_name)

                    // .child(imagekey+".jpg")

                    .child("image" + ".jpg")

                    // .child(System.currentTimeMillis() + "." + getFileExtension(filepathUri));

                    //storageReference

                    .putFile(filepathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


//                            storageReference
//                           // FirebaseStorage.getInstance().getReference("Vendors")
//                                    .child(firebaseAuth.getCurrentUser().getUid())
//                                    .child("Rent Car Information")
//                                    .child(carmodal_name)
//
//                                    .child("image1" + ".jpg").getDownloadUrl()
//
//                                   //.child(imagekey+".jpg").getDownloadUrl()
//
//                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                @Override
//                                public void onSuccess(Uri uri) {
//
////                                    HashMap hashMap=new HashMap();
////                                    hashMap.put("CarModalName",carmodal_name);
////                                    hashMap.put("CarModalNumber",carmodal_no);
////                                    hashMap.put("CarModalType",carmodal_type);
////                                    hashMap.put("CarModalPrice",carmodal_price);
////                                    hashMap.put("CarModalCondition",carmodal_condition);
////                                    hashMap.put("CarModalImageUrl",uri.toString());
////


                                    databaseReference_Vendors
                                  //  FirebaseDatabase.getInstance().getReference("Vendors")
                                            .child(firebaseAuth.getCurrentUser().getUid())
                                            .child("Rent Car Information")
                                            .child(carmodal_name)
                                            .setValue(hashMap)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Data stored Succesfully", Toast.LENGTH_SHORT).show();

                                        }
                                    });


                                }
                            });





                            final String downloadUrl = taskSnapshot.getDownloadUrl().toString();

                            RentCarsShow_ModalClass_Helper_VendorApp rentCarsShow_helper =
                                    new RentCarsShow_ModalClass_Helper_VendorApp(carmodal_price, carmodal_name, carmodal_no
                                            , carmodal_type, carmodal_condition, downloadUrl);


//                            Handler handler = new Handler();
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
//                                }
//                            }, 5000);

                          //  String uploadId = databaseReference_Vendors.push().getKey();

                            databaseReference_Vendors.child(firebaseAuth.getCurrentUser().getUid())
                                    .child("Rent Car Information")
                                    .child(carmodal_name)
                                    .setValue(rentCarsShow_helper);

                            //  progressDialog.dismiss();

                            Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Data stored", Toast.LENGTH_SHORT).show();

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Data Not stored", Toast.LENGTH_SHORT).show();
                }
            });

//            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progressDialog.setProgress((int) progress);
//                }
//            });


        } catch (Exception e) {

            Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Exception\n" + e.getMessage() + e.getMessage(), Toast.LENGTH_SHORT).show();

        }










        try {

                final String carmodal_name = textView_carmodal_name.getText().toString();
                final String carmodal_no = textView_carmodal_no.getText().toString();
                final String carmodal_price = textView_carprice_perhour.getText().toString();
                final String carmodal_type = textView_cartype.getText().toString();
                final String carmodal_condition = textView_carcondition.getText().toString();


            firebaseAuth = FirebaseAuth.getInstance();
            databaseReference_Vendors = FirebaseDatabase.getInstance().getReference("Vendors");
            storageReference = FirebaseStorage.getInstance().getReference("Vendors");


            final String userid=FirebaseAuth.getInstance().getCurrentUser().getUid();



          StorageReference storageReference1= storageReference
                               .child("Rent Car Information")
                               .child(carmodal_name)
                               .child(System.currentTimeMillis() + "." + getFileExtension(filepathUri));


                storageReference1
                    .putFile(filepathUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        progressBar.setProgress(0);
                                    }
                                }, 5000);


                                String car_image=taskSnapshot.getDownloadUrl().toString();

                                NewBooking_RentCarsShow_Helper_VendorApp rentCarsShow_id_helper =
                                        new NewBooking_RentCarsShow_Helper_VendorApp(carmodal_price, carmodal_name,
                                                carmodal_no, carmodal_type, carmodal_condition
                                                ,userid,car_image);



                               // String id=databaseReference_Vendors.push().getKey();

                                databaseReference_Vendors
                                .child("Rent Car Information")
                                        .child(carmodal_name)
                                        .setValue(rentCarsShow_id_helper);

                              //  progressDialog.dismiss();

                                Toast.makeText(Adding_RentCar_Information_VendorApp.this,
                                        "Data stored with image", Toast.LENGTH_SHORT).show();

                            }

                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Adding_RentCar_Information_VendorApp.this,
                                "data Not stored with image \n"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })



                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressBar.setProgress((int) progress);
                    }
                });




            } catch (Exception e) {

                Toast.makeText(Adding_RentCar_Information_VendorApp.this, "Exception\n" + e.getMessage() + e.getMessage(), Toast.LENGTH_SHORT).show();

            }





    }


*/































}










