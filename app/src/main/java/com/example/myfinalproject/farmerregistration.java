package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class farmerregistration extends AppCompatActivity {
    private EditText fullnamee,phonenumber,youraddress,pincode,cost;
    private Button registerbutton;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DBFarmer DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmerregistration);
        fullnamee = findViewById(R.id.farmer_name);
        phonenumber = findViewById(R.id.farmer_phone);
        youraddress = findViewById(R.id.farmer_address);
        pincode = findViewById(R.id.farmer_pin);
        cost=findViewById(R.id.farmer_cost);

        registerbutton = findViewById(R.id.farmer_reg);
        DB = new DBFarmer(this);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullnametxt = fullnamee.getText().toString();
                String phonetxt = phonenumber.getText().toString();
                String youraddresstxt = youraddress.getText().toString();
                String pincodetxt = pincode.getText().toString();
                int costtxt= Integer.parseInt(cost.getText().toString());
                //DB.update_cost();

                if(fullnametxt.equals("")||phonetxt.equals("")||youraddresstxt.equals("")||pincode.equals(""))
                    Toast.makeText(farmerregistration.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                        Boolean checkuser = DB.checkname(fullnametxt);
                        if(checkuser==false){
                            Boolean insert = DB.insertdata(fullnametxt,phonetxt,youraddresstxt,pincodetxt,costtxt);
                            if(insert==true){
                                Toast.makeText(farmerregistration.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),farmers.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(farmerregistration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(farmerregistration.this, "User already exists! please enter new farmer", Toast.LENGTH_SHORT).show();
                        }
                    }
                } });
    }
}
