package com.example.myfinalproject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class myloginpage extends AppCompatActivity {
    private EditText users, passwords;
    private Button loginbuttons;
    private TextView registernowbuttons;
    //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myloginpage);
        users = findViewById(R.id.usersemail);
        passwords = findViewById(R.id.password);
        loginbuttons = findViewById(R.id.loginbutton);
        registernowbuttons = findViewById(R.id.registernowbutton);
        DB = new DBHelper(this);
        registernowbuttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(myloginpage.this, myregisterpage.class));
            }
        });

        loginbuttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String usertext = users.getText().toString();
               // System.out.println(usertext);
                String passtext = passwords.getText().toString();
                if (usertext.equals("") || passtext.equals(""))
                    Toast.makeText(myloginpage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else if (usertext.equals("naidu@gmail.com") && passtext.equals("1234567")) {
                    Intent intent = new Intent(getApplicationContext(), farmers.class);
                    startActivity(intent);
                } else {
                    System.out.println(usertext);
                    Boolean checkuserpass = DB.checkusernamepassword(usertext, passtext);
                    if (checkuserpass) {

                        Toast.makeText(myloginpage.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
                        Intent intent2 = new Intent(getApplicationContext(),order.class);
                        intent2.putExtra("mail",usertext);
                        startActivity(intent);
                    } else {
                        Toast.makeText(myloginpage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        users.setError("Either username or password is wrong");
                    }
                }
            }
        });


    }


}