package com.example.myfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Customer.db";
    public static final String TABLE_NAME="Customers";
    public static final String COL_1 ="cmail";
    public DBHelper(Context context) {
        super(context, "Customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table Customers(cmail TEXT primary key,cname TEXT, cusphone INT,cusadd TEXT,cuspass TEXT)");
        MyDB.execSQL("create Table Customershistory(id INTEGER PRIMARY KEY, cmail TEXT ,orderdate TEXT,orderquan INTEGER,total INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists Customers");
        MyDB.execSQL("drop Table if exists Customershistory");
        onCreate(MyDB);

    }

    public Boolean insertData(String mail,String username,String phone,String address, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("cmail", mail);
        contentValues.put("cname", username);
        contentValues.put("cusphone", phone);
        contentValues.put("cusadd", address);
        contentValues.put("cuspass", password);

        long result = MyDB.insert("Customers", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String mail) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Customers where cmail = ?", new String[]{mail});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String mail, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Customers where cmail = ? and cuspass = ?", new String[] {mail,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean inserthistory(String mail,String order ,String quan,String total){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("cmail", mail);
        contentValues.put("orderdate", order);
        contentValues.put("orderquan",quan);
        contentValues.put("total", total);

        long result = MyDB.insert("Customershistory", null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    ArrayList<history> gethist(String cmail){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Customershistory WHERE cmail = ? ",new String[]{cmail});
        ArrayList<history> list= new ArrayList<>();

        while (cursor.moveToNext()){
            String date = (cursor.getString(2));
            String  milk =(cursor.getString(3));
            String total =( cursor.getString(4));
            String milk_given="0";
            list.add(new history(date,total,milk,milk_given));


        }
        return list;


}
    public String getUsername() throws SQLException {
        String username = "";
        Cursor cursor = this.getReadableDatabase().query(
                TABLE_NAME, new String[] { COL_1 },
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return username;
    }




}