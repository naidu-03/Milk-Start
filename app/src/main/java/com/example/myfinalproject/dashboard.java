package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class dashboard extends AppCompatActivity {
    ImageView ser,hist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ser=(ImageView) findViewById(R.id.search_button1);
        hist=(ImageView) findViewById(R.id.list);
        ser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(dashboard.this,mysearchpage.class);
                startActivity(intent);
            }
        });
        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(dashboard.this,customer_history.class);
                startActivity(intent);
            }
        });


    }
}