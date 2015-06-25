package com.example.dmdaid;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Displaystay extends Activity {

   int from_Where_I_Am_Coming = 0;
   private DBHelper mydb ;
   TextView day ;
   TextView reason;
   TextView nameHosp;
   TextView namePhy;
   TextView vital;
   TextView recommendation;
   TextView nurses;
   TextView date;
   TextView careplan;
   int id_To_Update = 0;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_display_stay);
      day = (TextView) findViewById(R.id.editText1);
      reason = (TextView) findViewById(R.id.editText2);
      nameHosp = (TextView) findViewById(R.id.editText3);
      namePhy = (TextView) findViewById(R.id.editText4);
      
      vital=(TextView) findViewById(R.id.editText5);
      recommendation=(TextView) findViewById(R.id.editText6);
      nurses=(TextView) findViewById(R.id.editText7);
      date = (TextView) findViewById(R.id.editText8);

      mydb = new DBHelper(this);

      Bundle extras = getIntent().getExtras(); 
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            //means this is the view part not the add stay part.
            Cursor rs = mydb.getStay(Value);
            id_To_Update = Value;
            rs.moveToFirst();
            String da = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_DAY));
            String reaso = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_REASON));
            String nameHos = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_NAMEHOSP));
            String namePh = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_NAMEPHY));
            String vita = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_VITAL));
            String recommendatio = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_RECOMMENDATION));
            String nurse = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_NURSES));
            String dat = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_DATE));
//            String carepla = rs.getString(rs.getColumnIndex(DBHelper.STAY_COLUMN_CAREPLAN));
            if (!rs.isClosed()) 
            {
               rs.close();
            }
            Button b = (Button)findViewById(R.id.button1);
            b.setVisibility(View.INVISIBLE);

            day.setText((CharSequence)da);
            day.setFocusable(false);
            day.setClickable(false);

            reason.setText((CharSequence)reaso);
            reason.setFocusable(false); 
            reason.setClickable(false);

            nameHosp.setText((CharSequence)nameHos);
            nameHosp.setFocusable(false);
            nameHosp.setClickable(false);

            namePhy.setText((CharSequence)namePh);
            namePhy.setFocusable(false); 
            namePhy.setClickable(false);
            
            
            vital.setText((CharSequence)vita);
            vital.setFocusable(false); 
            vital.setClickable(false);
            
            recommendation.setText((CharSequence)recommendatio);
            recommendation.setFocusable(false); 
            recommendation.setClickable(false);
            
            nurses.setText((CharSequence)nurse);
            nurses.setFocusable(false); 
            nurses.setClickable(false);

            date.setText((CharSequence)dat);
            date.setFocusable(false);
            date.setClickable(false);
           }
      }
   }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      Bundle extras = getIntent().getExtras(); 
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            getMenuInflater().inflate(R.menu.display_stay, menu);
         }
         else{
            getMenuInflater().inflate(R.menu.mainmenu, menu);
         }
      }
      return true;
   }

   public boolean onOptionsItemSelected(MenuItem item) 
   { 
      super.onOptionsItemSelected(item); 
      switch(item.getItemId()) 
   { 
      case R.id.Edit_Stay: 
      Button b = (Button)findViewById(R.id.button1);
      b.setVisibility(View.VISIBLE);
      day.setEnabled(true);
      day.setFocusableInTouchMode(true);
      day.setClickable(true);

      reason.setEnabled(true);
      reason.setFocusableInTouchMode(true);
      reason.setClickable(true);

      nameHosp.setEnabled(true);
      nameHosp.setFocusableInTouchMode(true);
      nameHosp.setClickable(true);

      namePhy.setEnabled(true);
      namePhy.setFocusableInTouchMode(true);
      namePhy.setClickable(true);
      
      vital.setEnabled(true);
      vital.setFocusable(false); 
      vital.setClickable(false);

      recommendation.setEnabled(true);
      recommendation.setFocusable(false); 
      recommendation.setClickable(false);

      nurses.setEnabled(true);
      nurses.setFocusable(false); 
      nurses.setClickable(false);

      
      date.setEnabled(true);
      date.setFocusableInTouchMode(true);
      date.setClickable(true);

      return true; 
      case R.id.Delete_Stay:

      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setMessage(R.string.deleteContact)
     .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            mydb.deleteStay(id_To_Update);
            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();  
            Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.Stay.class);
            startActivity(intent);
         }
      })
     .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            // User cancelled the dialog
         }
      });
      AlertDialog d = builder.create();
      d.setTitle("Are you sure");
      d.show();

      return true;
      default: 
      return super.onOptionsItemSelected(item); 

      } 
   } 

   public void run(View view)
   {	
      Bundle extras = getIntent().getExtras();
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            if(mydb.updateStay(id_To_Update, day.getText().toString(), reason.getText().toString(), nameHosp.getText().toString(), namePhy.getText().toString(), vital.getText().toString(),recommendation.getText().toString(),nurses.getText().toString(),date.getText().toString())){
               Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();	
               Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.Stay.class);
               startActivity(intent);
             }		
            else{
               Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();	
            }
		 }
         else{
            if(mydb.insertStay(day.getText().toString(), reason.getText().toString(), nameHosp.getText().toString(), namePhy.getText().toString(),vital.getText().toString(),recommendation.getText().toString(), nurses.getText().toString(),date.getText().toString())){
               Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
            }		
            else{
               Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
            }
            Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.Stay.class);
            startActivity(intent);
            }
      }
   }
}