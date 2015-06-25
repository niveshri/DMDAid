package com.example.dmdaid;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;

import com.aware.ESM;
import com.aware.providers.ESM_Provider.ESM_Data;

public class ESM_service extends IntentService {

	public ESM_service() {
		super("ESM service");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
//		Calendar today = Calendar.getInstance();
//		today.setTimeInMillis(System.currentTimeMillis());
//		today.set(Calendar.HOUR_OF_DAY, 1);
//		today.set(Calendar.MINUTE, 0);
//		today.set(Calendar.SECOND, 0);
//		
//		Cursor database_data = getContentResolver().query(ESM_Data.CONTENT_URI, null, ESM_Data.ANSWER_TIMESTAMP + ">" + today.getTimeInMillis() + " AND " + ESM_Data.STATUS + "=" + ESM.STATUS_ANSWERED, null, ESM_Data.ANSWER_TIMESTAMP + " DESC");
//		if( database_data != null && database_data.moveToFirst() ) {
//			//user already answered, reschedule alarm to tomorrow
//			SharedPreferences pref = getSharedPreferences("DMD", MODE_PRIVATE);
//			int hour = pref.getInt("hour", 0);
//			int minute = pref.getInt("minute", 0);
//			
//			Calendar tomorrow = Calendar.getInstance();
//			tomorrow.setTimeInMillis(System.currentTimeMillis());
//			tomorrow.set(Calendar.HOUR_OF_DAY, hour);
//			tomorrow.set(Calendar.MINUTE, minute);
//			tomorrow.set(Calendar.DAY_OF_MONTH, tomorrow.get(Calendar.DAY_OF_MONTH) + 1);
//			
//			Intent esm_service = new Intent(this, ESM_service.class);
//			PendingIntent pending_esm = PendingIntent.getService(this, 0, esm_service, PendingIntent.FLAG_UPDATE_CURRENT);
//			
//			AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//			alarm.setRepeating(AlarmManager.RTC_WAKEUP, tomorrow.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pending_esm);
//			
//			Log.d("DMD","Alarm set to:" + tomorrow.getTime().toLocaleString());
//			
//			return;
//		}
//		
//		//Check if there are unanswered/lost ESMs questions
//		Cursor lost_esm = getContentResolver().query(ESM_Data.CONTENT_URI, null, ESM_Data.STATUS + "=" + ESM.STATUS_NEW, null, null);
//		if( lost_esm != null && lost_esm.moveToFirst()) {
//			do{
//				
//				ContentValues newStatus = new ContentValues();
//				newStatus.put(ESM_Data.STATUS, ESM.STATUS_EXPIRED);
//				
//				getContentResolver().update(ESM_Data.CONTENT_URI, newStatus, ESM_Data._ID + "=" + lost_esm.getInt(lost_esm.getColumnIndex(ESM_Data._ID)), null);
//				
//			}while(lost_esm.moveToNext());
//		}
//		if( lost_esm != null && ! lost_esm.isClosed());
		
		String esm_text = "["
				+ "{'esm':{" +
				"'esm_type':" + ESM.TYPE_ESM_RADIO + "," +
				"'esm_title': 'Anxiety'," +
				"'esm_radios':['Never','Almost Never','Sometimes','often','Almost always']," +
				"'esm_instructions': 'I often feel worried and scared'," +
				"'esm_submit': 'Next'," +
				"'esm_expiration_threashold': 120," + //the user has 120 seconds to respond. Set to 0 to disable
				"'esm_trigger': 'DMD'" +
				"}},"+

		        "{'esm':{" +
				"'esm_type':" + ESM.TYPE_ESM_RADIO + "," +
				"'esm_title': 'Fatique'," +
				"'esm_instructions': 'I often feel too tired or exhausted to engage in activities'," +
				"'esm_radios':['Never','Almost Never','Sometimes','often','Almost always']," +
				"'esm_submit': 'Next'," +
				"'esm_expiration_threashold': 120," + //the user has 120 seconds to respond. Set to 0 to disable
				"'esm_trigger': 'DMD'" +
				"}},"+
				
				"{'esm':{" +
						"'esm_type':" + ESM.TYPE_ESM_RADIO + "," +
						"'esm_title': 'Peer relationships'," +
						"'esm_instructions': 'I feel good about my friendships and the time I spent with my friends'," +
						"'esm_radios':['Never','Almost Never','Sometimes','often','Almost always']," +
						"'esm_submit': 'Next'," +
						"'esm_expiration_threashold': 120," + //the user has 120 seconds to respond. Set to 0 to disable
						"'esm_trigger': 'DMD'" +
						"}},"+
						
						"{'esm':{" +
								"'esm_type':" + ESM.TYPE_ESM_RADIO + "," +
								"'esm_title': 'Global'," +
								"'esm_instructions': 'I am satisfied and feel good about my quality of life'," +
								"'esm_radios':['Never','Almost Never','Sometimes','often','Almost always']," +
								"'esm_submit': 'Next'," +
								"'esm_expiration_threashold': 120," + //the user has 120 seconds to respond. Set to 0 to disable
								"'esm_trigger': 'DMD'" +
								"}},"+
				
		        "{'esm':{" +
				"'esm_type':" + ESM.TYPE_ESM_RADIO + "," +
				"'esm_title': 'ESM Radio'," +
				"'esm_instructions': 'Are you able to eat?'," +
				"'esm_radios':['No','Yes','Not at all']," +
				"'esm_submit': 'Next'," +
				"'esm_expiration_threashold': 120," + //the user has 120 seconds to respond. Set to 0 to disable
				"'esm_trigger': 'DMD'" +
				"}}]";
		
		Intent esm_request = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);
		esm_request.putExtra(ESM.EXTRA_ESM, esm_text);
		sendBroadcast(esm_request);
		
		Log.d("DMD","ESM QUEUED" + esm_text);
	}
}
