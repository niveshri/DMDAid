package com.example.dmdaid;

import com.example.data.HospitalJ;
import com.example.data.HospitalDataSource;
import com.example.util.Utils;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class HospitalView extends Activity {

	HospitalDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospital_view);
		dataSource = new HospitalDataSource(this);
		Integer id = getIntent().getIntExtra("id", -1);
		if(id == -1) {
			Log.d(Utils.TAG, "No id specified");
		}
		
		dataSource.open();
		HospitalJ hosp = dataSource.getHospitalJ(id);
		dataSource.close();
		
		TextView tv = (TextView)findViewById(R.id.day1);
		tv.setText(hosp.Day);
		
		tv = (TextView)findViewById(R.id.reason1);
		tv.setText(""+hosp.Reason);
		
		
		tv = (TextView)findViewById(R.id.physician);
		tv.setText(hosp.NamePhy);
		
		
		tv = (TextView)findViewById(R.id.hosp1);
		tv.setText(""+hosp.NameHosp);
		
		
//		tv = (TextView)findViewById(R.id.timesper1);
//		tv.setText(hosp.timesPer);
//		
//		
//		tv = (TextView)findViewById(R.id.startMonth1);
//		tv.setText(hosp.startMonth);
//		
//		
//		tv = (TextView)findViewById(R.id.endMonth1);
//		tv.setText(hosp.endMonth);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hospital_view, menu);
		return true;
	}

}
