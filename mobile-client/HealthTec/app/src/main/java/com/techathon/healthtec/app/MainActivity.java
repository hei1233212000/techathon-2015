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
import android.app.job.JobInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.techathon.healthtec.model.Exercise;
import com.techathon.healthtec.util.JSONUtil;
import com.techathon.healthtec.util.RestfulGetActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ListView listView;
    private JobInfo.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set the background job for retrieving new exercise request
        blackgroundJob();
        // get the exercises
        final MainActivity thisActivity = this;
        new RestfulGetActivity() {
            @Override
            public void onPostExecute(String results) {
                Log.e("results = ", results);
                if (results != null) {
                    List<Exercise> exercises = JSONUtil.JSONToObject(results);
                    // list the exercise
                    thisActivity.listView = (ListView) thisActivity.findViewById(R.id.list);

                    ListView lv = (ListView) thisActivity.findViewById(R.id.list);
                    ListAdapter adapter = new ListAdapter(thisActivity, exercises);
                    if (lv != null) {
                        lv.setAdapter(adapter);
                        cancel(false /* mayInterruptIfRunning */);
                    }
                }
            }
        }.execute("https://healthtec.herokuapp.com/api/v1/exercises/?client-id=1", "ken.poon@dotcus.com", "12345");
    }

    public void onClick(View arg0) {
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
        Notification notify=new Notification(android.R.drawable.stat_notify_more,"MediCise",System.currentTimeMillis());
        PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
        notify.setLatestEventInfo(getApplicationContext(), "MediCise reminder", "Dr.Chan reminds you to have a run now.",pending);

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {

            synchronized public void run() {
                int i = 0;
                NotificationManager NM;
                NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification(android.R.drawable.stat_notify_more,"Testing Titile",System.currentTimeMillis());
                PendingIntent pending=PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
                notify.setLatestEventInfo(getApplicationContext(), "MediCise reminder", "Dr.Chan reminds you to have a run now.", pending);
                NM.notify(i, notify);
                i++;
            }

        }, 5000, 5000);
    }
}
