package com.example.jameel.everica.VendorApp.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jameel.everica.R;
import com.example.jameel.everica.VendorApp.LoginwithemailActivity_VendorsApp;

public class SplashActivityVendorApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_vendor_app);


        Thread thread=new Thread(){

            @Override
            public void run() {

                try{
                    sleep(2000);
                }
                catch (Exception e){

                    e.printStackTrace();
                }
                finally {

                    Intent intent=new Intent(SplashActivityVendorApp.this,LoginwithemailActivity_VendorsApp.class);
                    startActivity(intent);
                }

            }
        };
        thread.start();
    }


}

