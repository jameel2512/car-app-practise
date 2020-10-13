package com.example.jameel.everica.VendorApp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jameel.everica.R;
import com.example.jameel.everica.UsersApp.Login_Signup.LoginwithEmailActivity_UserApp;
import com.example.jameel.everica.VendorApp.Helpers.ProfileInfoHelper_VendorsApp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity_VendorsApp extends AppCompatActivity {

    EditText editText_username, editText_contact, editText_password, editText_email,editText_confirmpassword;
    Button button_SignUp;
    TextView textView_alreadyAccount;

    Button button_upload_profilepic;
    ImageView imageView;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__vendors_app);



        editText_contact = (EditText) findViewById(R.id.id_signup_contact);
        editText_email = (EditText) findViewById(R.id.id_signup_email);
        editText_password = (EditText) findViewById(R.id.id_signup_password);
        editText_confirmpassword = (EditText) findViewById(R.id.id_signup_confirmpassword);
        editText_username = (EditText) findViewById(R.id.id_signup_username);

        button_SignUp = (Button) findViewById(R.id.id_signup_Button);
        textView_alreadyAccount = (TextView) findViewById(R.id.id_signup_alreadyAccount);

        databaseReference = FirebaseDatabase.getInstance().getReference("Vendors");
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);



        textView_alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(SignupActivity_VendorsApp.this, LoginwithemailActivity_VendorsApp.class);
                startActivity(intent1);
            }
        });


        button_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String name = editText_username.getText().toString().trim();
                final String email = editText_email.getText().toString().trim();
                final String password = editText_password.getText().toString().trim();
                final String confirm_password = editText_confirmpassword.getText().toString().trim();
                final String contact_no = editText_contact.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {

                    Toast.makeText(SignupActivity_VendorsApp.this, "Please Enter Your name", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(SignupActivity_VendorsApp.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(contact_no)) {

                    Toast.makeText(SignupActivity_VendorsApp.this, "Please Enter Your name Phone no", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(SignupActivity_VendorsApp.this, "Please Enter Your password", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(confirm_password)) {

                    Toast.makeText(SignupActivity_VendorsApp.this, "Please Enter Your Confirm password", Toast.LENGTH_SHORT).show();
                }

//                if(confirm_password!=password)
//                {
//                    Toast.makeText(RegisterActivity_UserApp.this, "your Confirm Password is not matched", Toast.LENGTH_SHORT).show();
//                }

                else {

                    progressDialog.setTitle("Creating New Account");
                    progressDialog.setMessage("Please Wait for a while");
                    progressDialog.setCanceledOnTouchOutside(true);
                    progressDialog.show();


                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        ProfileInfoHelper_VendorsApp userProfile_info = new ProfileInfoHelper_VendorsApp
                                                (name, email, contact_no, password,confirm_password);

                                        databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                                                .child("users profile information")
                                                .setValue(userProfile_info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {

                                                    Toast.makeText(SignupActivity_VendorsApp.this, "Your Account Is Created\n" + email + "\n" + password, Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();

                                                    Intent intent1 = new Intent(SignupActivity_VendorsApp.this,
                                                            LoginwithemailActivity_VendorsApp.class);
                                                    startActivity(intent1);


                                                } else {

                                                    Toast.makeText(SignupActivity_VendorsApp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();


                                                }
                                            }

                                        });
                                    } else {

                                        Toast.makeText(SignupActivity_VendorsApp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();


                                    }

                                }
                            });



                }

            }
        });





    }
}
