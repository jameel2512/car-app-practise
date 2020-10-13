package com.example.jameel.everica.UsersApp.Login_Signup;

import android.app.ProgressDialog;
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
import com.example.jameel.everica.UsersApp.StartActivity_UsersApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginwithEmailActivity_UserApp extends AppCompatActivity {

    EditText editText_password,editText_email;
    Button button_Login;
    TextView textView_createNewAcc,textView_loginwithPhone;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email_user_app);

        editText_email=(EditText) findViewById(R.id.id_loginemail);
        editText_password=(EditText) findViewById(R.id.id_loginpassword);
        button_Login=(Button)findViewById(R.id.id_loginButton);

        textView_createNewAcc=(TextView) findViewById(R.id.id_loginCreateNewAccount);
        textView_loginwithPhone=(TextView) findViewById(R.id.id_login_login_withPhone);

        databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


        textView_createNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginwithEmailActivity_UserApp.this,
                        SignupActivity_UsersApp.class);
                startActivity(intent);

//                Intent intent=new Intent(LoginwithEmailActivity_UserApp.this,com.example.jameel.everica.RegisterActivity_UserApp.class);
//                startActivity(intent);

            }
        });



        textView_loginwithPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(LoginwithEmailActivity_UserApp.this,LoginwithPhoneActivity_UserApp.class);
                startActivity(intent);
            }
        });



        button_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email=editText_email.getText().toString();
                final String password=editText_password.getText().toString();

                //  LoginUser(email,password);


                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(LoginwithEmailActivity_UserApp.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginwithEmailActivity_UserApp.this, "Please Enter Your password", Toast.LENGTH_SHORT).show();
                }
                else {

                    progressDialog.setTitle("Login");
                    progressDialog.setMessage("Please Wait for a while");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();


                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginwithEmailActivity_UserApp.this,new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful())
                                    {
//
                                        Toast.makeText(LoginwithEmailActivity_UserApp.this, "You Are Login Succesfully\n"+email
                                                +"\n"+password, Toast.LENGTH_SHORT).show();

                                        Intent intent=new Intent(LoginwithEmailActivity_UserApp.this
                                                ,StartActivity_UsersApp.class);
                                        startActivity(intent);

//                                        Intent intent=new Intent(LoginwithEmailActivity_UserApp.this
//                                                ,Location_And_DateTime_Activity_UsersApp.class);
//                                        startActivity(intent);


//                                        Intent intent=new Intent(LoginwithEmailActivity_UserApp.this
//                                                ,DashBoard_UsersApp.class);
//                                        startActivity(intent);

                                        progressDialog.dismiss();

                                    }else
                                    {
                                        Toast.makeText(LoginwithEmailActivity_UserApp.this, "Error \n "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();

                                    }

                                }
                            });

                }


            }

        });








    }



}

