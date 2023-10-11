package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import android.widget.Toast;

public class order extends AppCompatActivity {
   TextView orde;
   Button order;
   DBFarmer db1;
   DBHelper db2;
    String mailtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orde=findViewById(R.id.textView9);
        order=findViewById(R.id.order);
        db1=new DBFarmer(this);
        db2=new DBHelper(this);
        Intent intent = getIntent();
        mailtxt= db2.getUsername();
         System.out.println(mailtxt);

        int milk=Integer.parseInt(intent.getStringExtra("fmilk"));
       int quan = Integer.parseInt(intent.getStringExtra("quantity"));
        int id = Integer.parseInt(intent.getStringExtra("fid"));
        int cost = Integer.parseInt(intent.getStringExtra("fcost"));
         String total =Integer.toString(cost*quan);
         int sold=Integer.parseInt(db1.get_sold(Integer.toString(id)))+quan;
         int milkgiven= db1.get_milk(Integer.toString(id));
        db1.update_remain_milk(id,sold,milk-quan);
        orde.setText(" farmer with id "+id+" will get you the milk of quantity"+ quan + "L "+"\nTotal Cost : "+ total +"Rs");
        //Toast.makeText(order.this, quan, Toast.LENGTH_SHORT).show();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        db1.inserthistory(id,dtf.format(now),cost,milkgiven,quan);
       db2.inserthistory(mailtxt,dtf.format(now),Integer.toString(quan),Integer.toString(cost));
      order.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent inte=new Intent(getApplicationContext(),mysearchpage.class);
              startActivity(inte);
          }
      });

    }

}