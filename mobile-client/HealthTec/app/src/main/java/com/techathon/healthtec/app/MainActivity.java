/*
 * (c) 2015 by Harry Chan
 * All rights reserved. No part of this document may be reproduced or
 * transmitted in any form or by any means, electronic, mechanical,
 * photocopying, recording, or otherwise, without prior written
 * permission of Harry Chan.
 */
package com.techathon.healthtec.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.techathon.healthtec.location.MyCurrentLocationListener;
import com.techathon.healthtec.util.RestfulGetActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //findViewById(R.id.my_button).setOnClickListener(this);
        // Get ListView object from xml
        blackgroundJob();
        listView = (ListView) findViewById(R.id.list);
        String[] values = new String[]{"Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        ArrayList<String> mStrings = new ArrayList<String>();
        mStrings.add("Item 1");
        mStrings.add("Item 2");
        mStrings.add("Item 3");
        mStrings.add("Item 4");

        ListView lv = (ListView) findViewById(R.id.list);
        ListAdapter adapter = new ListAdapter(this, mStrings);
        lv.setAdapter(adapter);



        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //       android.R.layout.simple_list_item_1, android.R.id.text1, values);

        // Assign adapter to ListView
      //  listView.setAdapter(adapter);
        // listView.setBackground(R.drawable.selector);

        // ListView Item Click Listener
       /* listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });*/
        Log.e("MY CURRENT LOCATION", "Start Location Log");
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        MyCurrentLocationListener locationListener = new MyCurrentLocationListener();
        //check by gps
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        //check by network
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        Log.e("MY CURRENT LOCATION", "End Location Log");
    }

    public void onClick(View arg0) {
       // Button b = (Button) findViewById(R.id.my_button);


       // b.setClickable(false);
      /*  new RestfulGetActivity() {
            @Override
            public void onPostExecute(String results) {
                if (results != null) {
                    log.e("Here Result:",results);
                  //  EditText et = (EditText) findViewById(R.id.my_edit);
                  //  et.setText(results);
                }
            }
        }.execute("https://api-us.clusterpoint.com/100390/_retrieve.json", "ken.poon@dotcus.com", "12345");*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void blackgroundJob(){
        NotificationManager NM;
        NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(android.R.drawable.stat_notify_more,"Testing Titile",System.currentTimeMillis());
        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
        notify.setLatestEventInfo(getApplicationContext(), "Testing Subject", "Testing Body",pending);

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            synchronized public void run() {
                int i = 0;
                NotificationManager NM;
                NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification(android.R.drawable.stat_notify_more,"Testing Titile",System.currentTimeMillis());
                PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
                notify.setLatestEventInfo(getApplicationContext(), "Testing Subject", "Testing Body", pending);
                NM.notify(i, notify);
                i++;
            }

        }, 5000, 5000);
    }
}
