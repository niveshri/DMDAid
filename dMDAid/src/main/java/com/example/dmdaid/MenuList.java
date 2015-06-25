package com.example.dmdaid;

import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.ESM;
import com.example.data.ListItem;
import com.example.data.MenuDataSource;
import com.example.data.MySQLiteHelper;
import com.example.reports.ReportList;
import com.example.restbackend.MyResultReceiver;
import com.example.restbackend.MyResultReceiver.Receiver;
import com.example.restbackend.RestIntentService;
import com.example.util.AppConstants;
import com.example.util.Utils;

public class MenuList extends Activity implements Receiver{
	
	private NotificationManager mNotificationManager;
	   private int notificationID = 100;
	   private int numMessages = 0;
	   
	   
//	   public void onClick(View view) {
//           displayNotification();
//        }
	   
	   protected void displayNotification() {
		      Log.i("Start", "notification");

		      /* Invoking the default notification service */
		      NotificationCompat.Builder  mBuilder = 
		      new NotificationCompat.Builder(this);	

		      mBuilder.setContentTitle("Time for some questions");
		      mBuilder.setContentText("Its time to answer monthly ESM's");
		      mBuilder.setTicker("Reminder");
		      mBuilder.setSmallIcon(R.drawable.report_icon2);
		      
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTimeInMillis(System.currentTimeMillis());
		      
		      if(calendar.get(Calendar.DAY_OF_MONTH) == 21)
		      {
		    	  displayNotification();
		      }
		      
		      /* Increase notification number every time a new notification arrives */
		      mBuilder.setNumber(++numMessages);
		      
		      /* Creates an explicit intent for an Activity in your app */
		      Intent resultIntent = new Intent(this, MenuList.class);

		      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		      stackBuilder.addParentStack(MenuList.class);

		      /* Adds the Intent that starts the Activity to the top of the stack */
		      stackBuilder.addNextIntent(resultIntent);
		      PendingIntent resultPendingIntent =
		         stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		         );

		      mBuilder.setContentIntent(resultPendingIntent);

		      mNotificationManager =
		      (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		      /* notificationID allows you to update the notification later on. */
		      mNotificationManager.notify(notificationID, mBuilder.build());
		   }

		  
		   protected void updateNotification() {
		      Log.i("Update", "notification");

		      /* Invoking the default notification service */
		      NotificationCompat.Builder  mBuilder = 
		      new NotificationCompat.Builder(this);	

		      mBuilder.setContentTitle("Updated Message");
		      mBuilder.setContentText("You've got updated message.");
		      mBuilder.setTicker("Updated Message Alert!");
		      mBuilder.setSmallIcon(R.drawable.report_icon2);

		     /* Increase notification number every time a new notification arrives */
		      mBuilder.setNumber(++numMessages);
		      
		      /* Creates an explicit intent for an Activity in your app */
		      Intent resultIntent = new Intent(this, MenuList.class);

		      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		      stackBuilder.addParentStack(MenuList.class);

		      /* Adds the Intent that starts the Activity to the top of the stack */
		      stackBuilder.addNextIntent(resultIntent);
		      PendingIntent resultPendingIntent =
		         stackBuilder.getPendingIntent(
		            0,
		            PendingIntent.FLAG_UPDATE_CURRENT
		         );

		      mBuilder.setContentIntent(resultPendingIntent);

		      mNotificationManager =
		      (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		      /* Update the existing notification using same notification ID */
		      mNotificationManager.notify(notificationID, mBuilder.build());
		   }
		   
		   
		   // Notification code ends here 
	   
	   

	String listType; 
	ListView listView; 
	MenuDataSource dataSource;
	ProgressDialog progress;
	MyResultReceiver mReceiver;
	public static SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		preferences = getSharedPreferences("DMD", MODE_PRIVATE);
				
		setContentView(R.layout.list);
		
		Intent sms_service = new Intent(this, SMS_service.class);
		startService(sms_service);

		listView = (ListView)findViewById(R.id.listView2);
		listView.setTextFilterEnabled(true);
		mReceiver = new MyResultReceiver(new Handler());
		mReceiver.setReceiver(this);
		progress = new ProgressDialog(this);

		listType = getIntent().getStringExtra("listtype");
		if(listType == null) 
			listType = "DMDAid";
		setTitle(listType);

		dataSource = new MenuDataSource(getApplicationContext());
		dataSource.open();
		Cursor cursor = dataSource.getList(listType);
		dataSource.close();

		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list, RECORD_TYPES);


		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.general_rowlayout,
				cursor,
				new String[] {MySQLiteHelper.MENUS_LIST_ITEM},
				new int[]{R.id.textView1}, 
				SimpleCursorAdapter.FLAG_AUTO_REQUERY);

		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {

				dataSource.open();
				ListItem item = dataSource.getlistItem(id);
				System.out.println(item);
				
				dataSource.close();
				
				if(item.nextType.equals("list"))
				{
					Intent intent = new Intent(getApplicationContext(), MenuList.class);
					intent.putExtra("listtype", item.next);
					startActivity(intent);
					
				}
				else if(item.nextType.equals("report"))
				{
					Intent intent = new Intent(getApplicationContext(), ReportList.class);
					intent.putExtra("category", item.next);
					startActivity(intent);
				} 
				else if(item.nextType.equals("Medications")) {
					Intent intent = new Intent(getApplicationContext(),MedicationList.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
					
				}
				
				else if(item.nextType.equals("Contact")) {
					Intent intent = new Intent(getApplicationContext(),Contact.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				
				else if(item.nextType.equals("Add")) {
					Intent intent = new Intent(getApplicationContext(),MainActivity.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				
				else if(item.nextType.equals("med")) {
					Intent intent = new Intent(getApplicationContext(),NewMedication.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				else if(item.nextType.equals("link")) {
					Intent intent = new Intent(getApplicationContext(),Link.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				else if(item.nextType.equals("addphy")) {
					Intent intent = new Intent(getApplicationContext(),PhysicianContact.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				
				else if(item.nextType.equals("stay")) {
					Intent intent = new Intent(getApplicationContext(),Stay.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				else if(item.nextType.equals("care")) {
					Intent intent = new Intent(getApplicationContext(),Homecare.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				else if(item.nextType.equals("con")) {
					Intent intent = new Intent(getApplicationContext(),DisplayPhysician.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}
				
				
				
				else if(item.nextType.equals("LOG")) {
					Intent intent = new Intent(getApplicationContext(),HospitalList.class);
					intent.putExtra("type", item.next);
					startActivity(intent);
				}else {
					Log.d(AppConstants.TAG,"Any other type is not supported yet");
				}

				Toast.makeText(MenuList.this, "item.next=" + item.next, Toast.LENGTH_LONG).show();
				
			}
		});
		
		
		Intent start_aware = new Intent(this, Aware.class);
		startService(start_aware);

        if( Aware.getSetting(this, "study_id").length() == 0 ) {
            Intent joinStudy = new Intent(this, Aware_Preferences.StudyConfig.class);
            joinStudy.putExtra("study_url","https://api.awareframework.com/index.php/webservice/index/170/MGTa7eU6CLIM");
            startService(joinStudy);
        }

		//Activate sensors
//		Aware.setSetting(this, Aware_Preferences.DEBUG_FLAG, true);
//		Aware.setSetting(getApplicationContext(), Aware_Preferences.STATUS_WEBSERVICE, true);
//		Aware.setSetting(getApplicationContext(), Aware_Preferences.WEBSERVICE_SERVER, "https://api.awareframework.com/index.php/webservice/index/170/MGTa7eU6CLIM");
//		Aware.setSetting(getApplicationContext(), Aware_Preferences.FREQUENCY_WEBSERVICE, 30);
//
//		Aware.setSetting(getApplicationContext(), Aware_Preferences.STATUS_ESM, true);
		
		
		//Define the ESM to be displayed
		String esmString = "[{'esm':{'esm_type':"+ESM.TYPE_ESM_TEXT+",'esm_title':'ESM Freetext','esm_instructions':'The user can answer an open ended question.','esm_submit':'Next','esm_expiration_threashold':60,'esm_trigger':'AWARE Tester'}}]";
		
		//Apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_list, menu);
		return true;
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.sync:
			Intent intent = new Intent(this,RestIntentService.class);
			intent.putExtra(Utils.ACTION_TAG, "syncReports");
			intent.putExtra(Utils.RECEIVER_TAG, mReceiver);
			startService(intent);
			progress.setTitle("Downloading Reports");
			progress.setMessage("Please wait while reports are downloaded");
			progress.show();

			return true;
			
		case R.id.alarm:
			DialogFragment newFragment = new TimePickerFragment();
		    newFragment.show(getFragmentManager(), "timePicker");
		    return true;
		    
		
		case R.id.cleardb:
			dataSource.deleteDatabase();
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
	@Override
	public void onReceiveResult(int resultCode, Bundle resultData) {
		if(resultCode == 200) {
			String message = resultData.getString("message", null);
			if(message.equals("finish"))
				progress.dismiss();
			else 
				progress.setMessage(message);
		} else {
			progress.dismiss();
			Toast toast = new Toast(this);
			toast.setText("Could not download reports");
			toast.show();
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Intent sms_service = new Intent(this, SMS_service.class);
		stopService(sms_service);
	}

	@SuppressLint("NewApi")
	public static class TimePickerFragment extends DialogFragment
	implements TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			Dialog dialog = new TimePickerDialog(getActivity(), this, hour, minute,
					DateFormat.is24HourFormat(getActivity()));
			dialog.setTitle("Set the time for your daily questionaire");
			// Create a new instance of TimePickerDialog and return it
			return dialog;
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//			AlarmManager alarmMgr = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
//			Intent intent = new Intent(getActivity(), RestIntentService.class);
//			intent.putExtra(Utils.ACTION_TAG, "getesm");
//			PendingIntent pendingIntent = PendingIntent.getService(getActivity(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//			alarmMgr.cancel(pendingIntent);
//			Calendar alarmTime = Calendar.getInstance();
//			alarmTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
//			alarmTime.set(Calendar.MINUTE, minute);
//			alarmTime.set(Calendar.SECOND, 0);
//			alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
			
			AlarmManager alarmMgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
			
			Intent esm_service = new Intent(getActivity(), ESM_service.class);
			PendingIntent pending_esm = PendingIntent.getService(getActivity(), 0, esm_service, PendingIntent.FLAG_UPDATE_CURRENT);
			
			Calendar alarmTime = Calendar.getInstance();
			alarmTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			alarmTime.set(Calendar.MINUTE, minute);
			alarmTime.set(Calendar.SECOND, 0);
			
			alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pending_esm);
			
			Log.d("DMD","Alarm set to:" + alarmTime.getTime().toLocaleString());
			
			SharedPreferences.Editor editor = preferences.edit();
			editor.putInt("hour", hourOfDay);
			editor.putInt("minute", minute);
			editor.commit();
		}
	}
}


