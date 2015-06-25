package com.example.dmdaid;

import com.aware.ESM;
import com.aware.providers.ESM_Provider.ESM_Data;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

public class SMS_service extends Service {

	private static String esms_answers = "";
	private static ESM_Listener esm_listener = new ESM_Listener();
	
	/**
	 * Listen for events from ESM Queue
	 * @author Divya
	 *
	 */
	
	
	public static class ESM_Listener extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if( intent.getAction().equals(ESM.ACTION_AWARE_ESM_QUEUE_STARTED) ) {
				esms_answers = "";
			}
			if( intent.getAction().equals(ESM.ACTION_AWARE_ESM_ANSWERED ) ) {
				Cursor last_answer = context.getContentResolver().query(ESM_Data.CONTENT_URI, new String[]{ESM_Data.ANSWER}, null, null, ESM_Data.TIMESTAMP + " DESC LIMIT 1");
				if( last_answer != null && last_answer.moveToFirst() ) {
					esms_answers += last_answer.getString(last_answer.getColumnIndex(ESM_Data.ANSWER)) + ";";
				}
				if( last_answer != null && ! last_answer.isClosed()) last_answer.close();
			}
			
			if( intent.getAction().equals(ESM.ACTION_AWARE_ESM_QUEUE_COMPLETE) ) {
				
				String[] all_answers = esms_answers.split(";");
				int number_of_significant = 0;
				for(int i=0; i< all_answers.length; i++) {
					if( all_answers[i].matches("Often") ) {
						number_of_significant++;
					}
				}
				 
				if( number_of_significant > 3 ) {
					//send SMS
					try {
						SmsManager smsManager = SmsManager.getDefault();
						
																		
						String phoneNumber = "9122177441";
						smsManager.sendTextMessage(phoneNumber, null, "Your child needs additional care", null, null);
//						smsManager.sendTextMessage(phoneNumber, null, esms_answers, null, null);
						Log.d("SMS",esms_answers );
						
						}catch (Exception e) {
							Log.d("SMS","Message cannot be displayed" );
						}
				}
			}
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(ESM.ACTION_AWARE_ESM_ANSWERED);
		filter.addAction(ESM.ACTION_AWARE_ESM_QUEUE_STARTED);
		filter.addAction(ESM.ACTION_AWARE_ESM_QUEUE_COMPLETE);
		registerReceiver(esm_listener, filter);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(esm_listener);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
