package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class producers extends AppCompatActivity {
 RecyclerView listView;
 ArrayList<farmerslist> list;
 Myadapter myadapter;
 DBFarmer db1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producers);
        listView =findViewById(R.id.listview1);
        db1 =new DBFarmer(this);
        list=new ArrayList<>();
        //list=db1.listFarmers();
       // myadapter =new Myadapter(this,list);
        LinearLayoutManager linear =new LinearLayoutManager(producers.this, RecyclerView.VERTICAL,false);
        listView.setLayoutManager(linear);
        listView.setAdapter(myadapter);
    }
}