package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class farmers extends AppCompatActivity {
    private EditText update_milk,farmerid,idhist;
    private Button milk_up,f_reg,backlogin,history;
    DBFarmer DB1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmers);
        farmerid = findViewById(R.id.milkid);
        update_milk = findViewById(R.id.updatemilk);
        milk_up = findViewById(R.id.milkup);
        f_reg = findViewById(R.id.regi);
        idhist=findViewById(R.id.milid);
        history=findViewById(R.id.milkhist);
        backlogin=findViewById(R.id.back_to_login);
        DB1 = new DBFarmer(this);
        milk_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id  = farmerid.getText().toString();
                String milk = update_milk.getText().toString();
                Integer fid= Integer.parseInt(id);
                Float up_milk=Float.parseFloat(milk);
                if(id.equals("")||milk.equals(""))
                    Toast.makeText(farmers.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = DB1.checkid(fid);
                    if(checkuser==true){
                        Boolean Update = DB1.update_milk(fid,up_milk);
                        if(Update==true){
                            Toast.makeText(farmers.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),farmers.class);
                            startActivity(intent);
                }
            }
        }
            }}
        );
        f_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),farmerregistration.class);
                startActivity(intent);            }
        });
        backlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(getApplicationContext(),myloginpage.class);
                startActivity(intent2);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idtxt=idhist.getText().toString();

                Intent intent = new Intent(getApplicationContext(),farmers_history.class);
                intent.putExtra("id",idtxt);
                startActivity(intent);            }
        });

    }
}