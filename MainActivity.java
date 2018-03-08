package com.example.user.gridl_cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.example.user.gridl_cardview.Hospital.HospitalActivity;

public class MainActivity extends AppCompatActivity {

    ImageView iv_MainHospital;
    CardView card_hospital;
    ImageView pushNot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_MainHospital = (ImageView)findViewById(R.id.iv_MainHospital);
        card_hospital = (CardView)findViewById(R.id.card_hospital);

        pushNot = (ImageView)findViewById(R.id.pushNot);

        pushNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PushActivity.class);
                startActivity(i);
            }
        });





        card_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HospitalActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
