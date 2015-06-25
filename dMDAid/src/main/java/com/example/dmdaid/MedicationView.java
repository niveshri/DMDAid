package com.example.dmdaid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.data.Medication;
import com.example.data.MedicationDataSource;
import com.example.util.Utils;

public class MedicationView extends Activity {

	MedicationDataSource dataSource;
	TextView name ;
	TextView date ;
	TextView times ;
	TextView start ;
	TextView end ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication_view);
		dataSource = new MedicationDataSource(this);
		Integer id = getIntent().getIntExtra("id", -1);
		if(id == -1) {
			Log.d(Utils.TAG, "No id specified");
		}
		
		 name = (TextView) findViewById(R.id.medicationName);
	     date = (TextView) findViewById(R.id.dose1);
	     times = (TextView) findViewById(R.id.timesper1);
	     start = (TextView) findViewById(R.id.startMonth1);
	     end = (TextView) findViewById(R.id.endMonth1);
		
		dataSource.open();
		Medication medication = dataSource.getMedication(id);
		dataSource.close();
		
		Button b = (Button)findViewById(R.id.button1);
        b.setVisibility(View.INVISIBLE);
		
		TextView tv = (TextView)findViewById(R.id.medicationName);
		tv.setText(medication.medicationName);
		
		tv = (TextView)findViewById(R.id.dose1);
		tv.setText(""+medication.dose);
		tv = (TextView)findViewById(R.id.units1);
		tv.setText(medication.units);
		tv = (TextView)findViewById(R.id.times1);
		tv.setText(""+medication.times);
		tv = (TextView)findViewById(R.id.timesper1);
		tv.setText(medication.timesPer);
		tv = (TextView)findViewById(R.id.startMonth1);
		tv.setText(medication.startMonth);
		tv = (TextView)findViewById(R.id.endMonth1);
		tv.setText(medication.endMonth);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medication_view, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	   { 
	      super.onOptionsItemSelected(item); 
	      switch(item.getItemId()) 
	   { 
	      case R.id.edit_medication: 
	      Button b = (Button)findViewById(R.id.button1);
	      b.setVisibility(View.VISIBLE);
	      name.setEnabled(true);
	      name.setFocusableInTouchMode(true);
	      name.setClickable(true);

	      date.setEnabled(true);
	      date.setFocusableInTouchMode(true);
	      date.setClickable(true);

	      times.setEnabled(true);
	      times.setFocusableInTouchMode(true);
	      times.setClickable(true);

	      start.setEnabled(true);
	      start.setFocusableInTouchMode(true);
	      start.setClickable(true);

	      end.setEnabled(true);
	      end.setFocusableInTouchMode(true);
	      end.setClickable(true);
	      
	   }
		return true;
	      
	   }

}
