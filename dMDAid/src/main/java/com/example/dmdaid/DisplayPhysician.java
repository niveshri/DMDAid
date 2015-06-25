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

public class DisplayPhysician extends Activity {

   int from_Where_I_Am_Coming = 0;
   private DBHelper mydb ;
   TextView name ;
   TextView phone;
   TextView email;
   TextView street;
   TextView place;
   TextView physician;
   
   int id_To_Update = 0;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_display_physician);
      name = (TextView) findViewById(R.id.editTextName);
      phone = (TextView) findViewById(R.id.editTextPhone);
      email = (TextView) findViewById(R.id.editTextStreet);
      street = (TextView) findViewById(R.id.editTextEmail);
      place = (TextView) findViewById(R.id.editTextCity);
      physician = (TextView) findViewById(R.id.editText1);

      mydb = new DBHelper(this);

      Bundle extras = getIntent().getExtras(); 
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            //means this is the view part not the add contact part.
            Cursor rs = mydb.getPhysician(Value);
            id_To_Update = Value;
            rs.moveToFirst();
            String nam = rs.getString(rs.getColumnIndex(DBHelper.PHYSICIAN_COLUMN_NAME));
            String phon = rs.getString(rs.getColumnIndex(DBHelper.PHYSICIAN_COLUMN_PHONE));
            String emai = rs.getString(rs.getColumnIndex(DBHelper.PHYSICIAN_COLUMN_EMAIL));
            String stree = rs.getString(rs.getColumnIndex(DBHelper.PHYSICIAN_COLUMN_STREET));
            String plac = rs.getString(rs.getColumnIndex(DBHelper.PHYSICIAN_COLUMN_CITY));
            String physicia = rs.getString(rs.getColumnIndex(DBHelper.PHYSICIAN_COLUMN_PHYSICIAN));
            if (!rs.isClosed()) 
            {
               rs.close();
            }
            Button b = (Button)findViewById(R.id.button1);
            b.setVisibility(View.INVISIBLE);

            name.setText((CharSequence)nam);
            name.setFocusable(false);
            name.setClickable(false);

            phone.setText((CharSequence)phon);
            phone.setFocusable(false); 
            phone.setClickable(false);

            email.setText((CharSequence)emai);
            email.setFocusable(false);
            email.setClickable(false);

            street.setText((CharSequence)stree);
            street.setFocusable(false); 
            street.setClickable(false);

            place.setText((CharSequence)plac);
            place.setFocusable(false);
            place.setClickable(false);
            
            physician.setText((CharSequence)physicia);
            physician.setFocusable(false);
            physician.setClickable(false);
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
            getMenuInflater().inflate(R.menu.display_physician, menu);
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
      case R.id.Edit_Contact: 
      Button b = (Button)findViewById(R.id.button1);
      b.setVisibility(View.VISIBLE);
      name.setEnabled(true);
      name.setFocusableInTouchMode(true);
      name.setClickable(true);

      phone.setEnabled(true);
      phone.setFocusableInTouchMode(true);
      phone.setClickable(true);

      email.setEnabled(true);
      email.setFocusableInTouchMode(true);
      email.setClickable(true);

      street.setEnabled(true);
      street.setFocusableInTouchMode(true);
      street.setClickable(true);

      place.setEnabled(true);
      place.setFocusableInTouchMode(true);
      place.setClickable(true);
      
      physician.setEnabled(true);
      physician.setFocusableInTouchMode(true);
      physician.setClickable(true);

      return true; 
      case R.id.Delete_Physician:

      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setMessage(R.string.deleteContact)
     .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            mydb.deleteContact(id_To_Update);
            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();  
            Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.PhysicianContact.class);
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
            if(mydb.updatePhysician(id_To_Update,name.getText().toString(), phone.getText().toString(), email.getText().toString(), street.getText().toString(), place.getText().toString(),physician.getText().toString())){
               Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();	
               Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.PhysicianContact.class);
               startActivity(intent);
             }		
            else{
               Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();	
            }
		 }
         else{
            if(mydb.insertPhysician(name.getText().toString(), phone.getText().toString(), email.getText().toString(), street.getText().toString(), place.getText().toString(),physician.getText().toString())){
               Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
            }		
            else{
               Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
            }
            Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.PhysicianContact.class);
            startActivity(intent);
            }
      }
   }
}