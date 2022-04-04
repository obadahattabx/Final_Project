package edu.birzeit.projectpart1;

import android.content.ContentValues;
import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        db.execSQL("CREATE TABLE ApplayProprty(ID INTEGER PRIMARY KEY AUTOINCREMENT ,ID_AGANCY INTEGER,ID_TENANT INTEGER,ID_Property INTEGER)");


        db.execSQL("CREATE TABLE User(ID INTEGER PRIMARY KEY AUTOINCREMENT ,EMAIL TEXT, PASSWORD TEXT,TYPE TEXT,NOTIFICATION TEXT)");
        db.execSQL("CREATE TABLE UserRentingAgency(ID INTEGER PRIMARY KEY  ,EMAIL TEXT, PASSWORD TEXT," +
                "NAME TEXT,COUNTRY TEXT,CITY TEXT,PHONE TEXT)");
        db.execSQL("CREATE TABLE UserTenant(ID INTEGER PRIMARY KEY  ,EMAIL TEXT, PASSWORD TEXT," +
                "FIRSTNAME TEXT,LASTNAME TEXT,GENDER TEXT,NATIONLITY TEXT,SALARY TEXT ," +
                "OCCUPATION TEXT,FAMILYSIZE TEXT,COUNTRY TEXT,CITY TEXT,PHONE TEXT)");

        db.execSQL("CREATE TABLE Property(ID INTEGER PRIMARY KEY AUTOINCREMENT  ,ADDRESS TEXT,CITY TEXT,SURFACEAREA real," +
                "CONSTRUCTIONYEAR TEXT, NUMBEDROOMS INTEGERt,PRICE real,AVAILABILITYDATE DATE,STATUS TEXT,CREATDATE DATE,DISCRIPTION TEXT," +
                "IMAGE BLOB,GARDEN TEXT,BALCONY TEXT,ID_TENANT INTEGER,ID_AGANCY INTEGER,VALID TEXT)");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

      //  db.execSQL("drop Table if exists User");

        db.execSQL("drop Table    User");
        db.execSQL("drop Table  UserRentingAgency");
        db.execSQL("drop Table  UserTenant");

        onCreate(db);



    }


     public void addUserRentingAgency(UserRentingAgency UR){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("ID", addUser(UR.getEmail(),UR.getPassword(),"AGANCY"));
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
        contentValues.put("ID", addUser(UT.getEmail(),UT.getPassword(),"TENANT"));
        contentValues.put("EMAIL", UT.getEmail());
        contentValues.put("PASSWORD", UT.getPassword());
        contentValues.put("FIRSTNAME", UT.getFirstName());
        contentValues.put("LASTNAME", UT.getLastName());
        contentValues.put("FIRSTNAME", UT.getFirstName());
        contentValues.put("GENDER", UT.getGender());
        contentValues.put("NATIONLITY", UT.getNationality());
        contentValues.put("SALARY", UT.getSalary());
        contentValues.put("OCCUPATION", UT.getOccuptaion());
        contentValues.put("FAMILYSIZE", UT.getFamilySize());
        contentValues.put("COUNTRY", UT.getCountry());
        contentValues.put("CITY", UT.getCity());
        contentValues.put("PHONE", UT.getPhoneNumber());
        sqLiteDatabase.insert("UserTenant", null, contentValues);

    }


    public long addUser(String email,String password,String type){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL",email);
        contentValues.put("PASSWORD", password);
        contentValues.put("TYPE",type);
        contentValues.put("NOTIFICATION","FALSE");
         return sqLiteDatabase.insert("User", null, contentValues);

    }


    public boolean check_notification(int id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where NOTIFICATION = ? AND ID=?", new String[] {"TRUE",String.valueOf(id)});

        if(cursor.getCount()>0) {
            return true;
        }
        else
            return false;
    }
    public boolean check_notification_TenantAccept(int id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where NOTIFICATION = ? AND ID=?", new String[] {"TRUE ACCEPT",String.valueOf(id)});

        if(cursor.getCount()>0) {
            return true;
        }
        else
            return false;
    }

    public boolean check_notification_Tenantrejct(int id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where NOTIFICATION = ? AND ID=?", new String[] {"TRUE REJECT",String.valueOf(id)});

        if(cursor.getCount()>0) {
            return true;
        }
        else
            return false;
    }



    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from User where EMAIL = ? and PASSWORD = ?", new String[] {username,password});

        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            MainActivity.id_user_login=cursor.getInt(0);
            System.out.println("----------------"+cursor.getInt(0));

            MainActivity.type_user=cursor.getString(3);
            return true;
        }
        else
            return false;
    }


    public void insertjasonProperty(Properties p){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ADDRESS", p.getAddress());
        contentValues.put("CITY", p.getCityName());
        contentValues.put("PRICE", p.getPrice());
        contentValues.put("GARDEN", p.getGarden());
        contentValues.put("BALCONY", p.getBalcony());
        contentValues.put("SURFACEAREA", p.getSurfaceArea());
        contentValues.put("CONSTRUCTIONYEAR", p.getConstructionYear());
        contentValues.put("NUMBEDROOMS", p.getNumOfBedroom());
        contentValues.put("AVAILABILITYDATE", p.getAvailabilDate());
        contentValues.put("STATUS", p.getStatus());
        contentValues.put("CREATDATE", p.getCreatDate());
        contentValues.put("DISCRIPTION", p.getDescription());
        contentValues.put("IMAGE", p.getImage());
        contentValues.put("ID_AGANCY",p.getID_agancy());
        contentValues.put("VALID","TRUE");

        sqLiteDatabase.insert("Property", null, contentValues);
    }


    public void insertPostProperty(Properties p){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ADDRESS", p.getAddress());
        contentValues.put("CITY", p.getCityName());
        contentValues.put("PRICE", p.getPrice());
        contentValues.put("GARDEN", p.getGarden());
        contentValues.put("BALCONY", p.getBalcony());
        contentValues.put("SURFACEAREA", p.getSurfaceArea());
        contentValues.put("CONSTRUCTIONYEAR", p.getConstructionYear());
        contentValues.put("NUMBEDROOMS", p.getNumOfBedroom());
        contentValues.put("AVAILABILITYDATE", p.getAvailabilDate());
        contentValues.put("STATUS", p.getStatus());
        contentValues.put("CREATDATE", p.getCreatDate());
        contentValues.put("DISCRIPTION", p.getDescription());
        contentValues.put("IMAGE", p.getImage());
        contentValues.put("ID_AGANCY",String.valueOf(MainActivity.id_user_login));
        contentValues.put("VALID","TRUE");

        sqLiteDatabase.insert("Property", null, contentValues);
    }



    public Boolean checkProperty_id(int id){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("select * from Property where ID=?",new String[]{String.valueOf(id)});

        if(cursor.getCount()>0) {
            // sent notifiction
            return false;
        }
        else
            return true;
    }




    public ArrayList<Properties> getAllProperties(){
        SQLiteDatabase sb=this.getReadableDatabase();
        ArrayList<Properties> allP=new ArrayList<Properties>();
        Cursor cursor=sb.rawQuery("select * from Property  where VALID= ?",new String[]{"TRUE"});
        if(cursor.moveToFirst()){
            do {
                Properties p=new Properties();
                p.setID(Integer.valueOf(cursor.getString(0)));
                p.setAddress(cursor.getString(1));
                p.setCityName(cursor.getString(2));
                p.setSurfaceArea(cursor.getString(3));
                p.setConstructionYear(cursor.getString(4));
                p.setNumOfBedroom(cursor.getString(5));
                p.setPrice(cursor.getString(6));
                p.setAvailabilDate(cursor.getString(7));
                p.setStatus(cursor.getString(8));
                p.setCreatDate(cursor.getString(9));
               p.setDescription(cursor.getString(10));
                p.setImage(cursor.getBlob(11));
                p.setGarden(cursor.getString(12));
                p.setBalcony(cursor.getString(13));
                p.setID_tenant(cursor.getInt(14));
                p.setID_agancy(cursor.getInt(15));
                p.setValid(cursor.getString(16));
                allP.add(p);


            }while (cursor.moveToNext());

        }
        return allP;
    }



    public ArrayList<Properties> getProperties_Search(String cityname,String min_area,String max_area,String min_bedroom,String max_bedroom,
                                                      String price,String status,String garden,String balcony){
        SQLiteDatabase sb=this.getReadableDatabase();
        ArrayList<Properties> allP=new ArrayList<Properties>();

        String query=query_search( cityname, min_area, max_area, min_bedroom, max_bedroom,
                 price, status, garden, balcony);

        Cursor cursor=sb.rawQuery(query.toString(),null);
        Log.i("jas",cursor.getCount()+"   =");
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
                p.setDescription(cursor.getString(10));
                p.setImage(cursor.getBlob(11));
                p.setGarden(cursor.getString(12));
                p.setBalcony(cursor.getString(13));
                p.setID_tenant(cursor.getInt(14));
                p.setID_agancy(cursor.getInt(15));
                p.setValid(cursor.getString(16));
                allP.add(p);


            }while (cursor.moveToNext());

        }
        return allP;
    }




    public String query_search(String cityname,String min_area,String max_area,String min_bedroom,String max_bedroom,
                               String price,String status,String garden,String balcony){
        StringBuilder sb=new StringBuilder();
        sb.append("select * from Property where ");
        if(cityname!="")
            sb.append(" CITY=\""+cityname +"\"and");
        if(min_area!="")
            sb.append(" SURFACEAREA >= "+min_area+" and");
        if(max_area!="")
            sb.append(" SURFACEAREA <="+ max_area +" and");
        if(min_bedroom!="")
            sb.append(" NUMBEDROOMS >="+ min_bedroom +" and");
        if(max_bedroom!="")
            sb.append(" NUMBEDROOMS <="+ max_bedroom+" and");
        if(price!="")
            sb.append(" PRICE="+price +" and");
        if(status!="")
            sb.append(" STATUS=\""+status +"\" and");
        if (garden!="")
            sb.append(" GARDEN=\""+garden+"\" and");
        if(balcony!="")
            sb.append(" BALCONY=\""+balcony+"\" and");

        sb.delete(sb.length()-3,sb.length());
        Log.i("qeury","this is  "+sb.toString());

                return sb.toString();
    }





    public ArrayList<Properties> getAllProperties_userAgancy(){
        SQLiteDatabase sb=this.getReadableDatabase();
        ArrayList<Properties> allP=new ArrayList<Properties>();
        Cursor cursor=sb.rawQuery("select * from Property where ID_AGANCY= ? AND VALID=?",new String[]{String.valueOf(MainActivity.id_user_login),"TRUE"});
        if(cursor.moveToFirst()){
            do {
                Properties p=new Properties();
                p.setID(cursor.getInt(0));
                p.setAddress(cursor.getString(1));
                p.setCityName(cursor.getString(2));
                p.setSurfaceArea(cursor.getString(3));
                p.setConstructionYear(cursor.getString(4));
                p.setNumOfBedroom(cursor.getString(5));
                p.setPrice(cursor.getString(6));
                p.setAvailabilDate(cursor.getString(7));
                p.setStatus(cursor.getString(8));
                p.setCreatDate(cursor.getString(9));
                p.setDescription(cursor.getString(10));
                p.setImage(cursor.getBlob(11));
                p.setGarden(cursor.getString(12));
                p.setBalcony(cursor.getString(13));
                p.setID_tenant(cursor.getInt(14));
                p.setID_agancy(cursor.getInt(15));
                p.setValid(cursor.getString(16));
                allP.add(p);


            }while (cursor.moveToNext());

        }
        return allP;
    }





    public UserRentingAgency getProfile_Agency(){
        SQLiteDatabase sb=this.getReadableDatabase();
        Cursor cursor=sb.rawQuery("select * from UserRentingAgency where ID= ?",new String[]{String.valueOf(MainActivity.id_user_login)});
        cursor.moveToFirst();
        UserRentingAgency ua=new UserRentingAgency();
        ua.setId(cursor.getInt(0));
        ua.setEmail(cursor.getString(1));
        ua.setPassword(cursor.getString(2));
        ua.setName(cursor.getString(3));
        ua.setCountry(cursor.getString(4));
        ua.setCity(cursor.getString(5));
        ua.setPhoneNumber(cursor.getString(6));
        return  ua;
    }
    public UserRentingAgency getProfile_Agency(int id){
        SQLiteDatabase sb=this.getReadableDatabase();
        Cursor cursor=sb.rawQuery("select * from UserRentingAgency where ID= ?",new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        UserRentingAgency ua=new UserRentingAgency();
        ua.setId(cursor.getInt(0));
        ua.setEmail(cursor.getString(1));
        ua.setPassword(cursor.getString(2));
        ua.setName(cursor.getString(3));
        ua.setCountry(cursor.getString(4));
        ua.setCity(cursor.getString(5));
        ua.setPhoneNumber(cursor.getString(6));
        return  ua;
    }




    public UserTenant getProfile_Tenant(){
        SQLiteDatabase sb=this.getReadableDatabase();
        Cursor cursor=sb.rawQuery("select * from UserTenant where ID= ?",new String[]{String.valueOf(MainActivity.id_user_login)});
        cursor.moveToFirst();
        UserTenant ut=new UserTenant();
        ut.setId(cursor.getInt(0));
        ut.setEmail(cursor.getString(1));
        ut.setPassword(cursor.getString(2));
        ut.setFirstName(cursor.getString(3));
        ut.setLastName(cursor.getString(4));
        ut.setGender(cursor.getString(5));
        ut.setNationality(cursor.getString(6));
        ut.setSalary(cursor.getString(7));
        ut.setOccuptaion(cursor.getString(8));
        ut.setFamilySize(cursor.getString(9));
        ut.setCountry(cursor.getString(10));
        ut.setCity(cursor.getString(11));
        ut.setPhoneNumber(cursor.getString(12));
        return  ut;
    }




    public UserTenant getProfile_Tenant(int id){
        SQLiteDatabase sb=this.getReadableDatabase();
        Cursor cursor=sb.rawQuery("select * from UserTenant where ID= ?",new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        UserTenant ut=new UserTenant();
        ut.setId(cursor.getInt(0));
        ut.setEmail(cursor.getString(1));
        ut.setPassword(cursor.getString(2));
        ut.setFirstName(cursor.getString(3));
        ut.setLastName(cursor.getString(4));
        ut.setGender(cursor.getString(5));
        ut.setNationality(cursor.getString(6));
        ut.setSalary(cursor.getString(7));
        ut.setOccuptaion(cursor.getString(8));
        ut.setFamilySize(cursor.getString(9));
        ut.setCountry(cursor.getString(10));
        ut.setCity(cursor.getString(11));
        ut.setPhoneNumber(cursor.getString(12));
        return  ut;
    }






    public  void Update_Ageny(UserRentingAgency UR){
         SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", UR.getEmail());
        contentValues.put("PASSWORD", UR.getPassword());
        contentValues.put("NAME", UR.getName());
        contentValues.put("COUNTRY", UR.getCountry());
        contentValues.put("CITY", UR.getCity());
        contentValues.put("PHONE", UR.getPhoneNumber());
        db.update("UserRentingAgency", contentValues, "ID=?", new String[]{String.valueOf(MainActivity.id_user_login)});

    }






    public void Update_Tenant(UserTenant UT){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", UT.getEmail());
        contentValues.put("PASSWORD", UT.getPassword());
        contentValues.put("FIRSTNAME", UT.getFirstName());
        contentValues.put("LASTNAME", UT.getLastName());
        contentValues.put("GENDER", UT.getGender());
        contentValues.put("NATIONLITY", UT.getNationality());
        contentValues.put("SALARY", UT.getSalary());
        contentValues.put("OCCUPATION", UT.getOccuptaion());
        contentValues.put("FAMILYSIZE", UT.getFamilySize());
        contentValues.put("COUNTRY", UT.getCountry());
        contentValues.put("CITY", UT.getCity());
        contentValues.put("PHONE", UT.getPhoneNumber());
        sqLiteDatabase.update("UserTenant", contentValues, "ID=?", new String[]{String.valueOf(MainActivity.id_user_login)});
    }





    public void Update_proprty(Properties p,String id){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ADDRESS", p.getAddress());
        contentValues.put("CITY", p.getCityName());
        contentValues.put("PRICE", p.getPrice());
        contentValues.put("GARDEN", p.getGarden());
        contentValues.put("BALCONY", p.getBalcony());
        contentValues.put("SURFACEAREA", p.getSurfaceArea());
        contentValues.put("CONSTRUCTIONYEAR", p.getConstructionYear());
        contentValues.put("NUMBEDROOMS", p.getNumOfBedroom());
        contentValues.put("AVAILABILITYDATE", p.getAvailabilDate());
        contentValues.put("STATUS", p.getStatus());
        contentValues.put("CREATDATE", p.getCreatDate());
        contentValues.put("DISCRIPTION", p.getDescription());
        contentValues.put("IMAGE", p.getImage());


        sqLiteDatabase.update("Property", contentValues, "ID=?", new String[]{id});

    }

    public void Update_proprtyAfterjason(Properties p,String id){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_TENANT",p.getID_tenant());
        contentValues.put("ID_AGANCY",p.getID_agancy());
        contentValues.put("VALID", "TRUE");
        contentValues.put("IMAGE", p.getImage());

        sqLiteDatabase.update("Property", contentValues, "ID=?", new String[]{id});

    }


    public void Update_proprtyAfterAccept(int id_tenant,String id,String valid){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_TENANT", id_tenant);
        contentValues.put("VALID", valid);
        sqLiteDatabase.update("Property", contentValues, "ID=?", new String[]{id});

    }



    public void Update_SetNotifiction(String id,String notification){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTIFICATION", notification);
        sqLiteDatabase.update("User", contentValues, "ID=?", new String[]{id});

    }





    public void delete_proprty(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Property", "ID=?", new String[]{id});

    }


    public void addUserApplayProperty(ApplayProperty AP){
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID_AGANCY", AP.getId_agancy());
        contentValues.put("ID_TENANT", AP.getId_tenant());
        contentValues.put("ID_Property", AP.getId_property());

        sqLiteDatabase.insert("ApplayProprty", null, contentValues);

    }


    public ArrayList<ApplayProperty> getAllApplay(int id_agancy){
        SQLiteDatabase sb=this.getReadableDatabase();
        ArrayList<ApplayProperty> allP=new ArrayList<ApplayProperty>();
        Cursor cursor=sb.rawQuery("select * from ApplayProprty where ID_AGANCY= ?",new String[]{String.valueOf(id_agancy)});
        if(cursor.moveToFirst()){
            do {
                ApplayProperty ap=new ApplayProperty();
                ap.setId(cursor.getInt(0));
                ap.setId_agancy(cursor.getInt(1));
                ap.setId_tenant(cursor.getInt(2));
                ap.setId_property(cursor.getInt(3));

                allP.add(ap);


            }while (cursor.moveToNext());

        }
        return allP;
    }

    public Boolean checkOfApplay(int id_agancy){
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor=MyDB.rawQuery("select * from ApplayProprty where ID_AGANCY= ?",new String[]{String.valueOf(id_agancy)});

        if(cursor.getCount()>0) {
            // sent notifiction
            return true;
        }
        else
            return false;
    }





    public void delete_applayproperty(int id_property){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("ApplayProprty", "ID_Property=?",new String[]{String.valueOf(id_property)});

    }



    public void delete_applay(int id_applay){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("ApplayProprty", "ID=?",new String[]{String.valueOf(id_applay)});

    }



    public Properties getProperty_byID(int id) {
        SQLiteDatabase sb = this.getReadableDatabase();
        Cursor cursor = sb.rawQuery("select * from Property where ID= ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();

        Properties p = new Properties();
        p.setID(cursor.getInt(0));
        p.setAddress(cursor.getString(1));
        p.setCityName(cursor.getString(2));
        p.setSurfaceArea(cursor.getString(3));
        p.setConstructionYear(cursor.getString(4));
        p.setNumOfBedroom(cursor.getString(5));
        p.setPrice(cursor.getString(6));
        p.setAvailabilDate(cursor.getString(7));
        p.setStatus(cursor.getString(8));
        p.setCreatDate(cursor.getString(9));
        p.setDescription(cursor.getString(10));
        p.setImage(cursor.getBlob(11));
        p.setGarden(cursor.getString(12));
        p.setBalcony(cursor.getString(13));
        p.setID_tenant(cursor.getInt(14));
        p.setID_agancy(cursor.getInt(15));
        p.setValid(cursor.getString(16));

        return  p;

    }

    public ArrayList<Properties> getAllProperties_userAgancy_history(){
        SQLiteDatabase sb=this.getReadableDatabase();
        ArrayList<Properties> allP=new ArrayList<Properties>();
        Cursor cursor=sb.rawQuery("select * from Property where ID_AGANCY= ? AND VALID=?",new String[]{String.valueOf(MainActivity.id_user_login),"FALSE"});
        if(cursor.moveToFirst()){
            do {
                Properties p=new Properties();
                p.setID(cursor.getInt(0));
                p.setAddress(cursor.getString(1));
                p.setCityName(cursor.getString(2));
                p.setSurfaceArea(cursor.getString(3));
                p.setConstructionYear(cursor.getString(4));
                p.setNumOfBedroom(cursor.getString(5));
                p.setPrice(cursor.getString(6));
                p.setAvailabilDate(cursor.getString(7));
                p.setStatus(cursor.getString(8));
                p.setCreatDate(cursor.getString(9));
                p.setDescription(cursor.getString(10));
                p.setImage(cursor.getBlob(11));
                p.setGarden(cursor.getString(12));
                p.setBalcony(cursor.getString(13));
                p.setID_tenant(cursor.getInt(14));
                p.setID_agancy(cursor.getInt(15));
                p.setValid(cursor.getString(16));
                allP.add(p);


            }while (cursor.moveToNext());

        }
        return allP;
    }



    public ArrayList<Properties> getAllProperties_userTenant_history(){
        SQLiteDatabase sb=this.getReadableDatabase();
        ArrayList<Properties> allP=new ArrayList<Properties>();
        Cursor cursor=sb.rawQuery("select * from Property where ID_TENANT= ? AND VALID=?",new String[]{String.valueOf(MainActivity.id_user_login),"FALSE"});
        if(cursor.moveToFirst()){
            do {
                Properties p=new Properties();
                p.setID(cursor.getInt(0));
                p.setAddress(cursor.getString(1));
                p.setCityName(cursor.getString(2));
                p.setSurfaceArea(cursor.getString(3));
                p.setConstructionYear(cursor.getString(4));
                p.setNumOfBedroom(cursor.getString(5));
                p.setPrice(cursor.getString(6));
                p.setAvailabilDate(cursor.getString(7));
                p.setStatus(cursor.getString(8));
                p.setCreatDate(cursor.getString(9));
                p.setDescription(cursor.getString(10));
                p.setImage(cursor.getBlob(11));
                p.setGarden(cursor.getString(12));
                p.setBalcony(cursor.getString(13));
                p.setID_tenant(cursor.getInt(14));
                p.setID_agancy(cursor.getInt(15));
                p.setValid(cursor.getString(16));
                allP.add(p);


            }while (cursor.moveToNext());

        }
        return allP;
    }

}
