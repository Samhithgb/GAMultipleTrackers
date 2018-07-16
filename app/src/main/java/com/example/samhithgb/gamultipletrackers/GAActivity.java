package com.example.samhithgb.gamultipletrackers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class GAActivity extends AppCompatActivity {
    private static final String TAG = "GAActivity";

    /*Try to send a single event to the following :
    1. Different accounts under same organisation
    2. Different accounts under different organisation
    */

    /*
    Setup Details :
    Organisation 1 :
    1. First Account Tracker ID : UA-122321198-1
    2. Second Account Tracker ID : UA-122306863-1
    */

    /* Organisation 2 :
    1. Tracker ID : UA-122324747-1
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ga);
    }

    private void sendEvent(String category, String action, String label, int value) {

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);

        //First Organization
        Tracker tracker1 = analytics.newTracker("UA-122321198-1");
        Tracker tracker2 = analytics.newTracker("UA-122306863-1");

        //Second Organization
        Tracker tracker3 = analytics.newTracker("UA-122324747-1");


        HitBuilders.EventBuilder event = new HitBuilders.EventBuilder();
        event.setCategory(category);
        event.setAction(action);
        if (!TextUtils.isEmpty(label)) {
            event.setLabel(label);
        }
        if (value > 0) {
            event.setValue(value);
        }
        tracker1.send(event.build());
        tracker2.send(event.build());
        tracker3.send(event.build());
        Log.d(TAG,"Category : " +category + " Action : "+ action + " Label : "+label);
    }

    public void onClick(View view) {
        sendEvent("Test Category","Test Event","Test Label", 1);
    }
}
