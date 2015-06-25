package com.example.dmdaid;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.text.format.DateFormat;

public class SimpleIntentService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
 
    public SimpleIntentService() {
        super("SimpleIntentService");
    }
 
    @Override
    protected void onHandleIntent(Intent intent) {
 
        String msg = intent.getStringExtra(PARAM_IN_MSG);
        SystemClock.sleep(30000); // 30 seconds
        String resultTxt = msg + " "
            + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
    }
}