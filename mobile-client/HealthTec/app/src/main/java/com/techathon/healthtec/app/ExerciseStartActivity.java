/*
 * (c) 2015 by Harry Chan
 * All rights reserved. No part of this document may be reproduced or
 * transmitted in any form or by any means, electronic, mechanical,
 * photocopying, recording, or otherwise, without prior written
 * permission of Harry Chan.
 */
package com.techathon.healthtec.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.techathon.healthtec.location.MyCurrentLocationListener;
import com.techathon.healthtec.model.Exercise;
import com.techathon.healthtec.model.MyLocation;
import com.techathon.healthtec.util.JSONUtil;
import com.techathon.healthtec.util.RestfulPutActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExerciseStartActivity extends ActionBarActivity {
	public static final String EXTRA_CURRENT_EXERCISE = "currentExercise";
	private Long startTime;
	private Handler handler = new Handler();
	ImageButton playButton,stopButton;
	LocationManager locationManager = null;
	MyCurrentLocationListener locationListener = null;
	Exercise currentExercise;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_start);
		currentExercise = (Exercise) getIntent().getExtras().get(EXTRA_CURRENT_EXERCISE);
		Log.e("currentExercise", currentExercise.toString());
		playButton =(ImageButton)findViewById(R.id.play_button);
		stopButton =(ImageButton)findViewById(R.id.stop_button);

		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationListener = new MyCurrentLocationListener();

		playButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				startTime = System.currentTimeMillis();
				handler.removeCallbacks(updateTimer);
				handler.postDelayed(updateTimer, 1000);
				//check by gps
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
			}
		});

		stopButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.removeCallbacks(updateTimer);
				locationManager.removeUpdates(locationListener);
				// send the location result to server
				ArrayList<Location> locationRecordList = locationListener.getLocationRecordList();
				List<MyLocation> locations = Lists.transform(locationRecordList, new Function<Location, MyLocation>() {
					@Override
					public MyLocation apply(Location location) {
						MyLocation myLocation = new MyLocation();
						myLocation.setLatitude(location.getLatitude());
						myLocation.setLongitude(location.getLongitude());
						return myLocation;
					}
				});
				Log.e("locations", locations.toString());
				if (!locations.isEmpty()) {
					String json = JSONUtil.ObjectToJSON(locations);
					Log.e("json", json);
					String url = "https://healthtec.herokuapp.com/api/v1/exercises/" + currentExercise.getId();
					Log.e("url", url);
					// use the fake data for testing
					// FIXME: it does not work
					String requestData = String.format("{\"path\":\"%s\"}", "Android");
					Log.e("requestData", requestData);
					new RestfulPutActivity()
							.execute(url, "ken.poon@dotcus.com",
									"12345", requestData);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_exercise_start, menu);
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

	private Runnable updateTimer = new Runnable() {
		public void run(){
			final TextView time = (TextView) findViewById(R.id.textView);
			Long spentTime = System.currentTimeMillis() - startTime;
			DecimalFormat formatter = new DecimalFormat("##");
			formatter.applyPattern("00");
			String hour = formatter.format(spentTime/1000/60/60);
			String mins = formatter.format((spentTime/1000)/60);
			String seconds = formatter.format(spentTime/1000 % 60);
			time.setText(hour+":"+mins+":"+seconds);
			handler.postDelayed(this, 1000);;
		}
	};

}
