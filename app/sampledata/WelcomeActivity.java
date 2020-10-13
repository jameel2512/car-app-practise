package com.example.jameel.everica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button button_driver,button_customer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



    button_customer=(Button) findViewById(R.id.id_btncust);
    button_driver=(Button) findViewById(R.id.id_btndriver);

    button_driver.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent_driver=new Intent(WelcomeActivity.this,DriverLoginRegister.class);
            startActivity(intent_driver);
            
        }
    });

//
//
//    button_customer.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//            Intent intent_customer=new Intent(WelcomeActivity.this,Users_Login_And_Register_Activity.class);
//            startActivity(intent_customer);
//        }
//    });
//




    }
}
