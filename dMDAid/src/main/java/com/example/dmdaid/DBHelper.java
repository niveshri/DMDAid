package com.example.dmdaid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

   public static final String DATABASE_NAME = "awareclient.db";
   public static final String CONTACTS_TABLE_NAME = "contacts";
   public static final String CONTACTS_COLUMN_ID = "id";
   public static final String CONTACTS_COLUMN_NAME = "name";
   public static final String CONTACTS_COLUMN_EMAIL = "email";
   public static final String CONTACTS_COLUMN_STREET = "street";
   public static final String CONTACTS_COLUMN_CITY = "place";
   public static final String CONTACTS_COLUMN_PHONE = "phone";
   public static final String CONTACTS_COLUMN_RELATION = "relation";
   
   
  
   public static final String STAY_TABLE_NAME = "stay";
   public static final String STAY_COLUMN_ID = "id";
   public static final String STAY_COLUMN_DAY = "day";
   public static final String STAY_COLUMN_REASON = "reason";
   public static final String STAY_COLUMN_NAMEHOSP = "nameHosp";
   public static final String STAY_COLUMN_NAMEPHY = "namePhy";
   public static final String STAY_COLUMN_VITAL= "vital";
   public static final String STAY_COLUMN_RECOMMENDATION = "recommendation";
   public static final String STAY_COLUMN_NURSES = "nurses";
   public static final String STAY_COLUMN_DATE = "date";
   public static final String STAY_COLUMN_CAREPLAN= "careplan";
   
   
  
   public static final String PHYSICIAN_TABLE_NAME = "physician";
   public static final String PHYSICIAN_COLUMN_ID = "id";
   public static final String PHYSICIAN_COLUMN_NAME = "name";
   public static final String PHYSICIAN_COLUMN_EMAIL = "email";
   public static final String PHYSICIAN_COLUMN_STREET = "street";
   public static final String PHYSICIAN_COLUMN_CITY = "place";
   public static final String PHYSICIAN_COLUMN_PHONE = "phone";
   public static final String PHYSICIAN_COLUMN_PHYSICIAN = "physician";

  
   public static final String CARE_TABLE_NAME = "care";
   public static final String CARE_COLUMN_TITLE = "title";
   public static final String CARE_COLUMN_REGIMEN = "regimen";
   public static final String CARE_COLUMN_DATE = "date";
   
   private HashMap hp;

   public DBHelper(Context context)
   {
      super(context, DATABASE_NAME , null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
      db.execSQL(
      "create table contacts " +
      "(id integer primary key, name text,phone text,email text, street text,place text, relation text)"
      );
      db.execSQL(
    	      "create table stay " +
    	      "(id integer primary key, day text,reason text,nameHosp text, namePhy text,vital text, recommendation text, nurses text, date text, careplan text)"
    	      );
      db.execSQL(
    	      "create table physician " +
    	      "(id integer primary key, name text,phone text,email text, street text,place text, physician text)"
    	      );
      db.execSQL(
    	      "create table care " +
    	      "(id integer primary key, title text,regimen text, date text)"
    	      );
   }
   
   

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
      db.execSQL("DROP TABLE IF EXISTS contacts");
      db.execSQL("DROP TABLE IF EXISTS stay");
      db.execSQL("DROP TABLE IF EXISTS physician");
      db.execSQL("DROP TABLE IF EXISTS care");

      onCreate(db);
   }

  
   
   public boolean insertContact  (String name, String phone, String email, String street,String place, String relation)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("name", name);
      contentValues.put("phone", phone);
      contentValues.put("email", email);	
      contentValues.put("street", street);
      contentValues.put("place", place);
      contentValues.put("relation", relation);
      
     
      db.insert("contacts", null, contentValues);
      return true;
      
      
   }
   
   public boolean insertPhysician  (String name, String phone, String email, String street,String place, String physician)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("name", name);
      contentValues.put("phone", phone);
      contentValues.put("email", email);	
      contentValues.put("street", street);
      contentValues.put("place", place);
      contentValues.put("physician", physician);
      
      

      db.insert("physician", null, contentValues);
      return true;
      
      
   }
   
   public boolean insertStay  (String day, String reason, String nameHosp, String namePhy, String vital, String recommendation, String nurses, String date)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("day", day);
      contentValues.put("reason", reason);
      contentValues.put("nameHosp", nameHosp);	
      contentValues.put("namePhy", namePhy);
      contentValues.put("vital", vital);
      contentValues.put("recommendation", recommendation);
      contentValues.put("nurses", nurses);
      contentValues.put("date", date);

      db.insert("stay", null, contentValues);
      return true;
      
      
   }
   
   
   public boolean insertCare  (String title, String regimen, String date)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();

      contentValues.put("title", title);
      contentValues.put("regimen", regimen);
      contentValues.put("date", date);
      db.insert("care", null, contentValues);
      return true;
      
      
   }
   

   
   
   public Cursor getData(int id){
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
      return res;
   }
   
   public Cursor getStay(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from stay where id="+id+"", null );
	      return res;
	   }
   
   public Cursor getPhysician(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from physician where id="+id+"", null );
	      return res;
	   }
   public Cursor getCare(int id){
	      SQLiteDatabase db = this.getReadableDatabase();
	      Cursor res =  db.rawQuery( "select * from care where id="+id+"", null );
	      return res;
	   }

   
   public int numberOfRows(){
      SQLiteDatabase db = this.getReadableDatabase();
      int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
      return numRows;
   }
   
   public int numberOfRow(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, STAY_TABLE_NAME);
	      return numRows;
	   }
   
   public int numberOfRo(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, PHYSICIAN_TABLE_NAME);
	      return numRows;
	   }
   
   public int numberOfR(){
	      SQLiteDatabase db = this.getReadableDatabase();
	      int numRows = (int) DatabaseUtils.queryNumEntries(db, CARE_TABLE_NAME);
	      return numRows;
	   }

   
   
   public boolean updateContact (Integer id, String name, String phone, String email, String street,String place, String relation)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("name", name);
      contentValues.put("phone", phone);
      contentValues.put("email", email);
      contentValues.put("street", street);
      contentValues.put("place", place);
      contentValues.put("relation", relation);
      db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
      return true;
   }

   public boolean updateStay (Integer id, String day, String reason, String nameHosp, String namePhy, String vital, String recommendation, String nurses, String date)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("day", day);
      contentValues.put("reason", reason);
      contentValues.put("nameHosp", nameHosp);
      contentValues.put("namePhy", namePhy);
      contentValues.put("vital", vital);
      contentValues.put("recommendation", recommendation);
      contentValues.put("nurses", nurses);
      contentValues.put("date", date);
      
      db.update("stay", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
      return true;
   }
   
   public boolean updatePhysician (Integer id, String name, String phone, String email, String street,String place, String physician)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("name", name);
      contentValues.put("phone", phone);
      contentValues.put("email", email);
      contentValues.put("street", street);
      contentValues.put("place", place);
      contentValues.put("physician", physician);
      
      db.update("physician", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
      return true;
   }
   
   public boolean updateCare (Integer id, String title, String regimen, String date)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("title", title);
      contentValues.put("regimen", regimen);
      contentValues.put("date", date);
      
      db.update("care", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
      return true;
   }

   
   public Integer deleteContact (Integer id)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete("contacts", 
      "id = ? ", 
      new String[] { Integer.toString(id) });
   }
   
   public Integer deleteStay (Integer id)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete("stay", 
      "id = ? ", 
      new String[] { Integer.toString(id) });
   }
   
   public Integer deletePhysician (Integer id)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete("physician", 
      "id = ? ", 
      new String[] { Integer.toString(id) });
   }
   
   public Integer deleteCare (Integer id)
   {
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete("care", 
      "id = ? ", 
      new String[] { Integer.toString(id) });
   }
   

   
   public ArrayList getAllCotacts()
   {
      ArrayList array_list = new ArrayList();
      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from contacts", null );
      res.moveToFirst();
      while(res.isAfterLast() == false){
      array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_RELATION)));
      res.moveToNext();
      }
   return array_list;
   }
   
   
   public ArrayList getAllStay()
   {
      ArrayList array_lists = new ArrayList();
      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from stay", null );
      res.moveToFirst();
      while(res.isAfterLast() == false){
      array_lists.add(res.getString(res.getColumnIndex(STAY_COLUMN_DATE)));
      res.moveToNext();
      }
   return array_lists;
   }
   
   public ArrayList getAllPhysician()
   {
      ArrayList array_lists = new ArrayList();
      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from physician", null );
      res.moveToFirst();
      while(res.isAfterLast() == false){
      array_lists.add(res.getString(res.getColumnIndex(PHYSICIAN_COLUMN_PHYSICIAN)));
      res.moveToNext();
      }
   return array_lists;
   }
   
   public ArrayList getAllCare()
   {
      ArrayList array_lists = new ArrayList();
      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res =  db.rawQuery( "select * from care", null );
      res.moveToFirst();
      while(res.isAfterLast() == false){
      array_lists.add(res.getString(res.getColumnIndex(CARE_COLUMN_TITLE)));
      res.moveToNext();
      }
   return array_lists;
   }

   
}

