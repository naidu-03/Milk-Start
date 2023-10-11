package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class customer_history extends AppCompatActivity {

    RecyclerView recyclerView;
    adapter adap;
    DBHelper  dbh;
ArrayList<history> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_history);
        recyclerView=findViewById(R.id.recycleview);
         list=new ArrayList<>();
         dbh=new DBHelper(this);
         String mail=dbh.getUsername();
         list=dbh.gethist(mail);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adap=new adapter(this,list);
        recyclerView.setAdapter(adap);

        adap.notifyDataSetChanged();

    }


}