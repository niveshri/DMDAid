package com.example.dmdaid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.data.HospitalDataSource;
import com.example.data.HospitalJ;
import com.example.util.Utils;

public class Hospital extends Activity {
	
	EditText DayEditText;
	EditText ReasonEditText;	
	EditText NameHospEditText;
	EditText NamePhyEditText;
	EditText VitalEditText;
	EditText RecommendationEditText;
	EditText NursesEditText;
	EditText DateEditText;
	EditText CareplanEditText;
	Button saveButton;
	
	
	HospitalDataSource dataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_hospital);
		
		dataSource = new HospitalDataSource(this);
		DayEditText = (EditText)findViewById(R.id.editText1);
		RecommendationEditText = (EditText)findViewById(R.id.editText5);
		ReasonEditText = (EditText)findViewById(R.id.editText1);
		NursesEditText = (EditText)findViewById(R.id.editText6);
		NameHospEditText=(EditText)findViewById(R.id.editText4);
		NamePhyEditText=(EditText)findViewById(R.id.editText6);
		VitalEditText=(EditText)findViewById(R.id.editText3);		
		DateEditText = (EditText)findViewById(R.id.editText7);
		CareplanEditText = (EditText)findViewById(R.id.editText8);
		
		saveButton = (Button)findViewById(R.id.button1);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HospitalJ hosp = new HospitalJ();
				
				if(isEmpty(DayEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Day not entered");
					toast.show();
					return;
				}
				hosp.Day = DayEditText.getText().toString();
				
				if(isEmpty(ReasonEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Reason not entered");
					toast.show();
					return;
				}
				hosp.Reason = ReasonEditText.getText().toString();
				
			
					if(isEmpty(RecommendationEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Recommendation not entered");
					toast.show();
					return;
				}
					
				hosp.Recommendation = RecommendationEditText.getText().toString();
				
				if(isEmpty(NameHospEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Name of the hospital not entered");
					toast.show();
					return;
				}
				
				hosp.NameHosp = NameHospEditText.getText().toString();
				
				if(isEmpty(NamePhyEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Name of the Physican not entered");
					toast.show();
					return;
				}
				
				hosp.NamePhy = NamePhyEditText.getText().toString();
				
				if(isEmpty(VitalEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Vital signs not entered");
					toast.show();
					return;
				}
				
				hosp.Vital = VitalEditText.getText().toString();
				
				if(isEmpty(NursesEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Nurses not entered");
					toast.show();
					return;
				}
				
				hosp.Nurses = NursesEditText.getText().toString();
				
				if(isEmpty(DateEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Date not entered");
					toast.show();
					return;
				}
				
				hosp.Date = DateEditText.getText().toString();
				
				if(isEmpty(CareplanEditText)) {
					Toast toast = new Toast(getApplicationContext());
					toast.setText("Careplan not entered");
					toast.show();
					return;
				}
				
				hosp.Careplan = CareplanEditText.getText().toString();				
				
				Log.d(Utils.TAG, "Saving Hospital stay log : " + hosp);
				dataSource.open();
				dataSource.createHospital(hosp);
				dataSource.close();
				Hospital.this.finish();
			}
		});
		
	}
	
	private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_hospital, menu);
		return true;
	}

}
