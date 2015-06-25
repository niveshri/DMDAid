package com.example.dmdaid;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

 

public class Addcontact extends Activity {

    public TextView outputText;

 

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);

        outputText = (TextView) findViewById(R.id.textView1);

       
    }
}