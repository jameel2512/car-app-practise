package com.example.jameel.everica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jameel.everica.UsersApp.Login_Signup.LoginwithEmailActivity_UserApp;
import com.example.jameel.everica.VendorApp.LoginwithemailActivity_VendorsApp;

public class VendorApp_OR_UsersApp_SelectActivity extends AppCompatActivity {


    Button button_VendorApp,button_UsersApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_app__or__users_app__select);



        button_UsersApp=(Button) findViewById(R.id.id_usersApp_btn);
        button_VendorApp=(Button) findViewById(R.id.id_vendorsApp_btn);



        button_UsersApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(VendorApp_OR_UsersApp_SelectActivity.this,LoginwithEmailActivity_UserApp.class);
                startActivity(intent);

            }
        });

        button_VendorApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(VendorApp_OR_UsersApp_SelectActivity.this,LoginwithemailActivity_VendorsApp.class);
                startActivity(intent);


            }
        });



    }
}
