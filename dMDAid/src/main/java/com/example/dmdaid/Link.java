package com.example.dmdaid;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;




public class Link extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        
        TextView mLink = (TextView) findViewById(R.id.link);
        if (mLink != null) {
          mLink.setMovementMethod(LinkMovementMethod.getInstance());
        }
          
        TextView mLinks = (TextView) findViewById(R.id.links);
        if (mLinks != null) {
          mLinks.setMovementMethod(LinkMovementMethod.getInstance());
        }
        TextView mLinke = (TextView) findViewById(R.id.linke);
        if (mLinke != null) {
          mLinke.setMovementMethod(LinkMovementMethod.getInstance());
        }
        
        TextView mLinked = (TextView) findViewById(R.id.linked);
        if (mLinked != null) {
          mLinked.setMovementMethod(LinkMovementMethod.getInstance());
        }
        
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_link, menu);
//        return true;
//    }
}