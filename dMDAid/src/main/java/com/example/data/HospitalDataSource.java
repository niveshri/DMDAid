package com.example.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.data.HospitalJ;
import com.example.util.AppConstants;
import com.example.util.Utils;

public class HospitalDataSource {
	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] hospTableColumns = { MySQLiteHelper.HOSPITALJ_ID,
			MySQLiteHelper.HOSPITALJ_DAY, 
			MySQLiteHelper.HOSPITALJ_REASON,
			MySQLiteHelper.HOSPITALJ_NAMEHOSP, 
			MySQLiteHelper.HOSPITALJ_NAMEPHY,
			MySQLiteHelper.HOSPITALJ_VITAL,
			MySQLiteHelper.HOSPITALJ_RECOMMENDATION,
			MySQLiteHelper.HOSPITALJ_NURSES,
			MySQLiteHelper.HOSPITALJ_DATE,
			MySQLiteHelper.HOSPITALJ_CAREPLAN};


	public HospitalDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
		dbHelper.createDataBase();
	}
	
	public void deleteDatabase() {
		dbHelper.deleteDatabase();
	}

	public void open() throws SQLException {
		//database = dbHelper.getWritableDatabase();
		database = dbHelper.openDataBase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createHospital(HospitalJ hosp) {

		ContentValues values = new ContentValues();
		if(hosp._id != null)
			values.put(MySQLiteHelper.HOSPITALJ_ID, hosp._id);
		values.put(MySQLiteHelper.HOSPITALJ_DAY, hosp.Day);
		values.put(MySQLiteHelper.HOSPITALJ_REASON, hosp.Reason);
		values.put(MySQLiteHelper.HOSPITALJ_NAMEHOSP, hosp.NameHosp);
		values.put(MySQLiteHelper.HOSPITALJ_NAMEPHY, hosp.NamePhy);
		values.put(MySQLiteHelper.HOSPITALJ_VITAL, hosp.Vital);
		values.put(MySQLiteHelper.HOSPITALJ_RECOMMENDATION, hosp.Recommendation);
		values.put(MySQLiteHelper.HOSPITALJ_NURSES, hosp.Nurses);
		values.put(MySQLiteHelper.HOSPITALJ_DATE, hosp.Date);
		values.put(MySQLiteHelper.HOSPITALJ_CAREPLAN, hosp.Careplan);
		long insertId = database.insert(MySQLiteHelper.HOSPITALJ_TABLE, null,
				values);
		if(insertId == -1)
			Log.d(AppConstants.TAG,"Could not create new stay log");
		else
			Log.d(AppConstants.TAG,"Inserted log in database:" + hosp.Day +":"+ hosp._id);
		return insertId;
	}

	public Cursor getHopitalForType(String date) {
		Cursor cursor = database.query(MySQLiteHelper.HOSPITALJ_TABLE,
				hospTableColumns, MySQLiteHelper.HOSPITALJ_DATE + "=?", new String[]{date}, null, null, null);
		cursor.moveToFirst();
		return cursor;
	}
	
	public void deleteHospital(long HospitalId) {
		
		database.delete(MySQLiteHelper.HOSPITALJ_TABLE, MySQLiteHelper.HOSPITALJ_ID
				+ " = " + HospitalId, null);
		Log.d(AppConstants.TAG,"Deleting LOGid:" +HospitalId);
		
	}
	
	public HospitalJ getHospitalJ(long id) {
		Cursor cursor = database.query(MySQLiteHelper.HOSPITALJ_TABLE,
				hospTableColumns, MySQLiteHelper.HOSPITALJ_ID + "=?", new String[]{""+id}, null, null, null);
		cursor.moveToFirst();
		
		return cursortoHospitalJ(cursor);
	}
	
	public void cleanHospitalJ(int id) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.HOSPITALJ_VITAL, 0);
		database.update(MySQLiteHelper.HOSPITALJ_TABLE, values, MySQLiteHelper.HOSPITALJ_ID + "=?", new String[]{""+id});
		Log.d(Utils.TAG, "Updating log: " + id + " to clean");
	}
	
	public List<HospitalJ> getvitalHospitalJ() {
		List<HospitalJ> hosp = new ArrayList<HospitalJ>();
		Cursor cursor = database.query(MySQLiteHelper.HOSPITALJ_TABLE,
				hospTableColumns, MySQLiteHelper.HOSPITALJ_VITAL + "=?", new String[]{""+1}, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			HospitalJ hospi = cursortoHospitalJ(cursor);
			hosp.add(hospi);
			cursor.moveToNext();
			Log.d(Utils.TAG, "VITAL id :" + hospi._id);
		}
		// make sure to close the cursor
		cursor.close();
		return hosp;	

	}
	
	private HospitalJ cursortoHospitalJ(Cursor cursor) {
		HospitalJ hosp = new HospitalJ();
		hosp._id = cursor.getInt(0);
		hosp.Day = cursor.getString(1);
		hosp.Reason = cursor.getString(2);
		hosp.NameHosp = cursor.getString(3);
		hosp.NamePhy= cursor.getString(4);
		hosp.Vital = cursor.getString(5);
		hosp.Recommendation = cursor.getString(6);
		hosp.Nurses = cursor.getString(7);
		hosp.Date = cursor.getString(8);
		hosp.Careplan = cursor.getString(9);
		return hosp;
	}
} 