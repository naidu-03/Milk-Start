package com.example.myfinalproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class mysearchpage extends AppCompatActivity {
    private Button butn;
    private TextView but1;
   private DBFarmer db1;
   private String pin;
   private ArrayList<farmerslist> list;
  private   Myadapter myadapter;
 private RecyclerView listView;

    private EditText search_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysearchpage);


        butn=findViewById(R.id.search_button);
        but1=findViewById(R.id.textView11);
        search_num=findViewById(R.id.simpleSearchView);

        list=new ArrayList<>();
        db1 =new DBFarmer(this);

        System.out.println(pin);
       //list=db1.getfarm("520007");
       // myadapter =new Myadapter(list,this);
        listView =findViewById(R.id.listview);
        // listView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mysearchpage.this, RecyclerView.VERTICAL, false);
        listView.setLayoutManager(linearLayoutManager);
        //listView.setLayoutManager(new LinearLayoutManager(this));
       // listView.setAdapter(myadapter);




        //myadapter.notifyDataSetChanged();
        if (list.size()>0)
            Toast.makeText(mysearchpage.this, "retrived successfull", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(mysearchpage.this, " retrieved unsuccessfull", Toast.LENGTH_SHORT).show();



        butn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          pin=search_num.getText().toString();
         db1.update_cost();
          list=db1.getfarm(pin);
          if(list.size()>0){
              but1.setVisibility(View.INVISIBLE);
              listView.setVisibility(View.VISIBLE);

          myadapter =new Myadapter(list,mysearchpage.this);
          listView.setAdapter(myadapter);
          myadapter.notifyDataSetChanged();

          for(int i=0;i<list.size();i++){
              Log.d("INFO", "onClick: ID "+list.get(i).getId()+"milk_cost "+list.get(i).getcost()+"milk_avail "+list.get(i).getavail());
          }

      }
          else{
              listView.setVisibility(View.INVISIBLE);
              but1.setVisibility(View.VISIBLE);
          }}

  });
    }
}

