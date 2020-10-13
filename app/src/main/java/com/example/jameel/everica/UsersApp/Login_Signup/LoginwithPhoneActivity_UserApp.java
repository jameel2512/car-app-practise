package com.example.jameel.everica.UsersApp.Login_Signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
//import com.example.jameel.everica.RegisterActivity_UserApp;
//import com.example.jameel.everica.SignupActivity2_UsersApp;
import com.example.jameel.everica.UsersApp.StartActivity_UsersApp;
import com.example.jameel.everica.VendorApp.SignupActivity_VendorsApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginwithPhoneActivity_UserApp extends AppCompatActivity {


    public EditText inputPhoneNumber, inputVercode;
    private Button verCodesendbtn, veriefybtn;
    private TextView loginwithemail,createnewAccount;

 //   String phoneNumber;

    public FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;


    private  String codesent,phonenumber,phonenumber_db,verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwith_phone__user_app);



        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");



        inputPhoneNumber = (EditText) findViewById(R.id.id_login_phoneNumber);
        inputVercode = (EditText) findViewById(R.id.id_login_verificationCode);
        verCodesendbtn = (Button) findViewById(R.id.id_login_sendverificationcodebtn);
        veriefybtn = (Button) findViewById(R.id.id_login_veriefybtn);
        createnewAccount=(TextView) findViewById(R.id.id_login_phone_createNewAccount);
        loginwithemail=(TextView) findViewById(R.id.id_login_loginwithemail);




//        try{
//
//            databaseReference
//                    .child("users profile information")
//                    .addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//
//                            phonenumber_db=dataSnapshot.child("contact").getValue().toString();
//
//                            Toast.makeText(LoginwithPhoneActivity_UserApp.this, ""+phonenumber_db, Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//
//        }
//        catch (Exception e){
//            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//
//        }



        loginwithemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginwithPhoneActivity_UserApp.this,LoginwithEmailActivity_UserApp.class);
                startActivity(intent);

            }
        });

        createnewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginwithPhoneActivity_UserApp.this,SignupActivity_VendorsApp.class);
                startActivity(intent);

            }
        });



        verCodesendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                onVerificationCodeSent();

            }
        });


        veriefybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vierifySignInCodeSent();

            }
        });


    }



    //code sent

    private void onVerificationCodeSent() {

         phonenumber = inputPhoneNumber.getText().toString();

//        if(phonenumber.isEmpty())
//        {
//            Toast.makeText(LoginwithPhoneActivity_UserApp.this, "Please Input a Phone Number", Toast.LENGTH_SHORT).show();
//
//        }else {
//
//            if(phonenumber.toString() == phonenumber_db.toString())
//            {
//                Toast.makeText(LoginwithPhoneActivity_UserApp.this, " phone number get from database Successfully : "+phonenumber_db, Toast.LENGTH_SHORT).show();
//
//                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                        phonenumber,
//                        60,
//                        java.util.concurrent.TimeUnit.SECONDS,
//                        this,
//                        mCallbacks);
//            }
//            else
//            {
//                Toast.makeText(LoginwithPhoneActivity_UserApp.this, "this number is not availiable in database ", Toast.LENGTH_SHORT).show();
//            }
//
//
//        }



        try {

            databaseReference
                    .child("users profile information")
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            phonenumber_db = dataSnapshot.child("contact").getValue().toString();

                            if(phonenumber_db.isEmpty())
                            {
                                Toast.makeText(LoginwithPhoneActivity_UserApp.this, "phone number didnt get from database ", Toast.LENGTH_SHORT).show();

                            }else {

                                Toast.makeText(LoginwithPhoneActivity_UserApp.this, " phone number get from database Successfully : "+phonenumber_db, Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });



        }catch (Exception e)
        {
            Toast.makeText(LoginwithPhoneActivity_UserApp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(phonenumber)) {

            Toast.makeText(LoginwithPhoneActivity_UserApp.this, "Input a number", Toast.LENGTH_SHORT).show();

        }
        else {

            if (inputPhoneNumber.toString() == phonenumber_db) {

                Toast.makeText(LoginwithPhoneActivity_UserApp.this, " phone number get from database Successfully : " + phonenumber_db, Toast.LENGTH_SHORT).show();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phonenumber,
                        60,
                        java.util.concurrent.TimeUnit.SECONDS,
                        this,
                        mCallbacks);
         }

 //        else {
//
//                Toast.makeText(LoginwithPhoneActivity_UserApp.this, "this number is not availiable in database ", Toast.LENGTH_SHORT).show();
//            }



        }



    }





    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            signInWithPhoneAuthCredential(phoneAuthCredential);

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Toast.makeText(LoginwithPhoneActivity_UserApp.this, e.getMessage()+"Invalid Phone Number , Please Enter Correct Phone number", Toast.LENGTH_SHORT).show();

        }

        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {

            codesent=verificationId;
        }



    };



    //veriefy code method start
    private void vierifySignInCodeSent() {

         verificationCode = inputVercode.getText().toString();

        if (TextUtils.isEmpty(verificationCode)) {

            Toast.makeText(LoginwithPhoneActivity_UserApp.this, "Please write Verification Code...", Toast.LENGTH_SHORT).show();

        }else
        {

            PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(codesent,verificationCode);
            signInWithPhoneAuthCredential(phoneAuthCredential);

        }


    }





    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(LoginwithPhoneActivity_UserApp.this, "Succesfully Login", Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(LoginwithPhoneActivity_UserApp.this
                                    ,StartActivity_UsersApp.class);
                            startActivity(intent);

                        } else {

                            String msg=task.getException().toString();

                            Toast.makeText(LoginwithPhoneActivity_UserApp.this, "Error : "+msg, Toast.LENGTH_SHORT).show();

                        }

                    }



                });

    }




}
