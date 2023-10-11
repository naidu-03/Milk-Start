package com.example.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class myregisterpage extends AppCompatActivity {
    private EditText useremaill,fullnamee,phonenumber,yoursaddress,passwords,conpasswords;
    private Button registerbutton;
    private TextView loginnowbutton;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
   DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myregisterpage);
        useremaill = findViewById(R.id.useremail);
        fullnamee = findViewById(R.id.fullname);
        phonenumber = findViewById(R.id.phonenum);
        yoursaddress = findViewById(R.id.youraddress);
        passwords = findViewById(R.id.password);
        conpasswords = findViewById(R.id.conpassword);
        registerbutton = findViewById(R.id.registerbutton);
        loginnowbutton = findViewById(R.id.loginnowbutton);
        DB = new DBHelper(this);
        loginnowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(myregisterpage.this,myloginpage.class));
            }
        });


        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usermailtxt = useremaill.getText().toString();
                String fullnametxt = fullnamee.getText().toString();
                String phonetxt = phonenumber.getText().toString();
                String youraddresstxt = yoursaddress.getText().toString();
                String passwordtxt = passwords.getText().toString();
                String conpasswordtxt = conpasswords.getText().toString();
                if(fullnametxt.isEmpty())
                {
                    fullnamee.setError("Please enter your fullname!");
                }
                else if(!usermailtxt.matches(emailPattern))
                {
                    useremaill.setError("Enter Correct Email");
                }
                else if(passwordtxt.isEmpty() || passwordtxt.length()<6)
                {
                    passwords.setError("Enter Proper Password");
                }
                else if(!conpasswordtxt.equals(conpasswordtxt))
                {
                    conpasswords.setError("Password Not Matched");
                }
                if(usermailtxt.equals("")||fullnametxt.equals("")||passwordtxt.equals("")||youraddresstxt.equals("")||conpasswordtxt.equals("")||!usermailtxt.matches(emailPattern)||passwordtxt.length()<6)
                    Toast.makeText(myregisterpage.this, "Please enter all the fields correctly", Toast.LENGTH_SHORT).show();
                else{
                    if(passwordtxt.equals(conpasswordtxt)){
                        Boolean checkuser = DB.checkusername(usermailtxt);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(usermailtxt,fullnametxt,phonetxt,youraddresstxt,passwordtxt);
                            if(insert==true){
                                Toast.makeText(myregisterpage.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(myregisterpage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(myregisterpage.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                            useremaill.setError("user already exists");

                        }
                    }else{
                        Toast.makeText(myregisterpage.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                        passwords.setError("password are not matching");

                    }
                } }
        });
        loginnowbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), myloginpage.class);
                startActivity(intent);
            }
        });
    }
}







