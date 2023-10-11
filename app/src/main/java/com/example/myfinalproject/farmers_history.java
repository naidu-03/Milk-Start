package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class farmers_history extends AppCompatActivity {

    RecyclerView recyclerView;
    adapter1 adap;
    DBFarmer  dbh;
    ArrayList<history> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers_history);
        recyclerView=findViewById(R.id.recycleview);
        list=new ArrayList<>();
        dbh=new DBFarmer(this);
        Intent intent = getIntent();
        String id =intent.getStringExtra("id");
        list=dbh.gethist(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adap=new adapter1(this,list);
        recyclerView.setAdapter(adap);

        adap.notifyDataSetChanged();

    }


}