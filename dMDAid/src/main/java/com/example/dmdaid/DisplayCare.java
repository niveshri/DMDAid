package com.example.dmdaid ;

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

public class DisplayCare extends Activity {

   int from_Where_I_Am_Coming = 0;
   private DBHelper mydb ;
   TextView title;
   TextView regimen;
   TextView date;
   
   
   int id_To_Update = 0;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_homecare);
      title = (TextView) findViewById(R.id.editText1);
      regimen = (TextView) findViewById(R.id.editText2);
      date=(TextView) findViewById(R.id.editText3);
     

      mydb = new DBHelper(this);

      Bundle extras = getIntent().getExtras(); 
      if(extras !=null)
      {
         int Value = extras.getInt("id");
         if(Value>0){
            //means this is the view part not the add contact part.
            Cursor rs = mydb.getCare(Value);
            id_To_Update = Value;
            rs.moveToFirst();
            String titl = rs.getString(rs.getColumnIndex(DBHelper.CARE_COLUMN_TITLE));
            String regime = rs.getString(rs.getColumnIndex(DBHelper.CARE_COLUMN_REGIMEN));
            String dat=rs.getString(rs.getColumnIndex(DBHelper.CARE_COLUMN_DATE));
           
            if (!rs.isClosed()) 
            {
               rs.close();
            }
            Button b = (Button)findViewById(R.id.button1);
            b.setVisibility(View.INVISIBLE);

            title.setText((CharSequence)titl);
            title.setFocusable(false);
            title.setClickable(false);

            regimen.setText((CharSequence)regime);
            regimen.setFocusable(false); 
            regimen.setClickable(false);
            
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
            getMenuInflater().inflate(R.menu.display_care, menu);
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
      case R.id.Edit_Care: 
      Button b = (Button)findViewById(R.id.button1);
      b.setVisibility(View.VISIBLE);
      title.setEnabled(true);
      title.setFocusableInTouchMode(true);
      title.setClickable(true);

      regimen.setEnabled(true);
      regimen.setFocusableInTouchMode(true);
      regimen.setClickable(true);

      date.setEnabled(true);
      date.setFocusableInTouchMode(true);
      date.setClickable(true);

      return true; 
      case R.id.Delete_Care:

      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setMessage(R.string.deletecare)
     .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int id) {
            mydb.deleteCare(id_To_Update);
            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();  
            Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.Homecare.class);
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
            if(mydb.updateCare(id_To_Update,title.getText().toString(), regimen.getText().toString(), date.getText().toString())){
               Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();	
               Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.Homecare.class);
              
               startActivity(intent);
             }		
            else{
               Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();	
            }
		 }
         else{
            if(mydb.insertCare(title.getText().toString(), regimen.getText().toString(),date.getText().toString())){
               Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();	
            }		
            else{
               Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();	
            }
            Intent intent = new Intent(getApplicationContext(),com.example.dmdaid.Homecare.class);
           
            startActivity(intent);
            }
      }
   }
}