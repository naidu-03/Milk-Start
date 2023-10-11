package com.example.myfinalproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.ArrayList;

public class DBFarmer extends SQLiteOpenHelper {
    //public static final String DBNAME = "Customer.db";
    String Table_name = "Farmers";

    public DBFarmer(Context context) {
        super(context, "Farmers.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table Farmers(FId INTEGER primary key AUTOINCREMENT, Fname TEXT,Faddress TEXT, Fpincode TEXT,Fphone INTEGER,milk_cost INTEGER)");
        MyDB.execSQL("create Table Farmershistory(FId INTEGER,given_date TEXT,milk_cost INTEGER,milk INTEGER,sold INTEGER)");
        MyDB.execSQL("create Table Milk_updater(FId INTEGER primary key AUTOINCREMENT,milk_cost INTEGER,milk_date TEXT,present_cost INTEGER, milk INTEGER,sold INTEGER,remaining INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Farmers");
        MyDB.execSQL("drop Table if exists Farmershistory");
        MyDB.execSQL("drop Table if exists milk_updater");

    }

    public Boolean insertdata(String Fname, String Faddress, String Fpincode, String Fphone, int milk_cost) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Fname", Fname);
        contentValues.put("Faddress", Faddress);
        contentValues.put("Fpincode", Fpincode);
        contentValues.put("Fphone", Fphone);
        contentValues.put("milk_cost", milk_cost);
        ContentValues contentvalues2 =new ContentValues();
        contentvalues2.put("milk_cost",milk_cost);
        long result = MyDB.insert("Farmers", null, contentValues);
        long result2=MyDB.insert("Milk_updater",null,contentvalues2);
        if ((result == -1) && (result2 ==-1)){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean update_milk(Integer fid, Float up_milk) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        int milk_cost=Get_cost(fid);
        LocalDateTime now = LocalDateTime.now();
        ContentValues contentValues = new ContentValues();
        contentValues.put("milk_date",now.toString());
        contentValues.put("present_cost",milk_cost);
        contentValues.put("milk", up_milk);
        contentValues.put("remaining", up_milk);
        contentValues.put("sold", 0);
        MyDB.update("Milk_updater", contentValues, "FId=?", new String[]{String.valueOf(fid)});
        return true;
    }

    public Boolean update_remain_milk(Integer fid, int sold, int remaining) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sold", sold);
        contentValues.put("remaining", remaining);
        MyDB.update("Milk_updater", contentValues, "FId=?", new String[]{String.valueOf(fid)});
        return true;
    }

    public Boolean checkid(Integer fid) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Farmers where FId= ?", new String[]{String.valueOf(fid)});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkname(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Farmers where Fname = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }



    public int get_milk(String id) {
        int milk = 0;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Milk_updater where FId = ?", new String[]{id});

        while (cursor.moveToNext()) {
            milk = Integer.parseInt(cursor.getString(4));
            cursor.close();
        }
        return milk;
    }

    ArrayList<farmerslist> getfarm(String pin) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT m.FId,m.present_cost,m.remaining FROM Milk_updater as m,Farmers as f WHERE m.FId=f.FId and m.remaining>0 and f.Fpincode = ? ", new String[]{pin});
        ArrayList<farmerslist> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String FId = (cursor.getString(0));
            String milk_cost = (cursor.getString(1));
            String milk = (cursor.getString(2));

            list.add(new farmerslist(FId, milk_cost, milk));


        }
        return list;
    }

    public String get_sold(String id) {
        String MILKsold = null;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Milk_updater where FId = ?", new String[]{id});

        while (cursor.moveToNext()) {
            MILKsold = (cursor.getString(5));
            cursor.close();
        }
        return MILKsold;
    }

    public Boolean inserthistory(int FId, String date, int milk_cost, int milk, int milk_quan) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FId", FId);
        contentValues.put("given_date", date);
        contentValues.put("milk_cost", milk_cost);
        contentValues.put("milk", milk);
        contentValues.put("sold", milk_quan);
        long result = MyDB.insert("Farmershistory", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    ArrayList<history> gethist(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Farmershistory WHERE FId = ? ", new String[]{id});
        ArrayList<history> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            String date = (cursor.getString(1));
            String milk_given = (cursor.getString(3));
            String total = (cursor.getString(2));
            String milk = (cursor.getString(4));

            list.add(new history(date, total, milk,milk_given));


        }
        return list;

    }
    public Integer Get_cost(Integer id) {
        Integer milk_cost=0;
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Milk_updater where FId = ?", new String[]{String.valueOf(id)});

        while (cursor.moveToNext()) {
            milk_cost = Integer.parseInt(cursor.getString(1));
            cursor.close();
        }
        return milk_cost;
    }


    public Boolean update_cost() {
        SQLiteDatabase MyDB = this.getWritableDatabase();
       // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        Cursor cursor = MyDB.rawQuery("Select * from Milk_updater ",null);
        if (cursor.moveToFirst()) {
            do{
            String id = cursor.getString(0);
                int cost=Integer.parseInt(cursor.getString(1));
                String time=cursor.getString(2);
                System.out.println(cost);
        LocalDateTime now2 = LocalDateTime.parse(time);
        System.out.println(now2);
        Duration duration = Duration.between(now2, now);
        int h=(int)duration.toMinutes();
        if(h<2){
            cost=cost;
        }
        else if (h>2 &&h<4){
            cost=cost-5;

            }
        else if (h>4&&h<6){
            cost=cost-8;
        }
        else{
            cost=cost-10;
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put("present_cost",cost);
        MyDB.update("Milk_updater", contentValues, "FId=?", new String[]{String.valueOf(id)});
        }while (cursor.moveToNext());}
        cursor.close();
        return true;
    }


    ArrayList<farmerslist> listFarmers(int id) {
        String sql = "select * from Farmers where Fpincode= ?";
        SQLiteDatabase MyDB = this.getReadableDatabase();
        ArrayList<farmerslist> list = new ArrayList<>();
        Cursor cursor = MyDB.rawQuery(sql, new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            do {
                String FId = (cursor.getString(0));
                String milk = (cursor.getString(6));
                String milk_cost = (cursor.getString(5));

                list.add(new farmerslist(FId, milk_cost, milk));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public Boolean checkpin(String name) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Farmers where Fpincode = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}