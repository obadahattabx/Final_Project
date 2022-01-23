package edu.birzeit.projectpart1;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper( Context context,
                           String name,
                           SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE User(ID INTEGER PRIMARY KEY AUTOINCREMENT ,EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("CREATE TABLE UserRentingAgency(ID INTEGER PRIMARY KEY  ,EMAIL TEXT, PASSWORD TEXT," +
                "NAME TEXT,COUNTRY TEXT,CITY TEXT,PHONE TEXT)");
        db.execSQL("CREATE TABLE UserTenant(ID INTEGER PRIMARY KEY  ,EMAIL TEXT, PASSWORD TEXT," +
                "FIRSTNAME TEXT,LASTNAME TEXT,GENDER TEXT,NATIONLITY TEXT,SALARY TEXT ," +
                "OCCUPATION TEXT,FAMILYSIZE TEXT,COUNTRY TEXT,CITY TEXT,PHONE TEXT)");

        db.execSQL("CREATE TABLE Property(ID INTEGER PRIMARY KEY AUTOINCREMENT ,ADDRESS TEXT,CITY TEXT,SURFACEAREA TEXT," +
                "CONSTRUCTIONYEAR TEXT, NUMBEDROOMS TEXT,PRICE TEXT,AVAILABILITYDATE DATE,STATUS TEXT,CREATDATE DATE,DISCRIPTION TEXT,IMAGE BLOB)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists User");
        db.execSQL("drop Table if exists UserRentingAgency");
       db.execSQL("drop Table if exists UserTenant");
         db.execSQL("drop Table if exists Property");

    }
     public void addUserRentingAgency(UserRentingAgency UR){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("ID", addUser(UR.getEmail(),UR.getPassword()));
         contentValues.put("EMAIL", UR.getEmail());
         contentValues.put("PASSWORD", UR.getPassword());
         contentValues.put("NAME", UR.getName());
         contentValues.put("COUNTRY", UR.getCountry());
         contentValues.put("CITY", UR.getCity());
         contentValues.put("PHONE", UR.getPhoneNumber());
         sqLiteDatabase.insert("UserRentingAgency", null, contentValues);


     }
    public void addUserTenant(UserTenant UT){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", addUser(UT.getEmail(),UT.getPassword()));
        contentValues.put("EMAIL", UT.getEmail());
        contentValues.put("PASSWORD", UT.getPassword());
        contentValues.put("FIRSTNAME", UT.getFirstName());
        contentValues.put("LASTNAME", UT.getLastName());
        contentValues.put("FIRSTNAME", UT.getFirstName());
        contentValues.put("GENEDER", UT.getFirstName());
        contentValues.put("NATIONLITY", UT.getNationality());
        contentValues.put("SALARY", UT.getCountry());
        contentValues.put("OCCUPATION", UT.getCountry());
        contentValues.put("FAMILYSIZE", UT.getCountry());
        contentValues.put("COUNTRY", UT.getCountry());
        contentValues.put("CITY", UT.getCity());
        contentValues.put("PHONE", UT.getPhoneNumber());
        sqLiteDatabase.insert("UserTenant", null, contentValues);

    }


    public long addUser(String email,String password){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD", password);
         return sqLiteDatabase.insert("User", null, contentValues);

    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where EMAIL = ? and PASSWORD = ?", new String[] {username,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public void insertPostProperty(Properties p){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ADDRESS", p.getAddress());
        contentValues.put("SURFACEAREA", p.getSurfaceArea());
        contentValues.put("CONSTRUCTIONYEAR", p.getConstructionYear());
        contentValues.put("NUMBEDROOMS", p.getNumOfBedroom());
        contentValues.put("AVAILABILITYDATE", p.getAvailabilDate());
        contentValues.put("STATUS", p.getStatus());
        contentValues.put("CREATDATE", p.getCreatDate());
      //  contentValues.put("DISCRIPTION", p.getDescription());
        contentValues.put("IMAGE", p.getImage());
        sqLiteDatabase.insert("Property", null, contentValues);
    }


    public ArrayList<Properties> getAllProperties(){
        SQLiteDatabase sb=this.getReadableDatabase();
        ArrayList<Properties> allP=new ArrayList<Properties>();
        Cursor cursor=sb.rawQuery("select * from Property",null);
        if(cursor.moveToFirst()){
            do {
                Properties p=new Properties();
                p.setAddress(cursor.getString(1));
                p.setCityName(cursor.getString(2));
                p.setSurfaceArea(cursor.getString(3));
                p.setConstructionYear(cursor.getString(4));
                p.setNumOfBedroom(cursor.getString(5));
                p.setPrice(cursor.getString(6));
                p.setAvailabilDate(cursor.getString(7));
                p.setStatus(cursor.getString(8));
                p.setCreatDate(cursor.getString(9));
              //  p.setDescription(cursor.getString(10));
                p.setImage(cursor.getBlob(10));
                allP.add(p);


            }while (cursor.moveToNext());

        }
        return allP;
    }



}
