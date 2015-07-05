/*
 * (c) 2015 by Harry Chan
 * All rights reserved. No part of this document may be reproduced or
 * transmitted in any form or by any means, electronic, mechanical,
 * photocopying, recording, or otherwise, without prior written
 * permission of Harry Chan.
 */
package com.techathon.healthtec.app;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ExerciseStartActivity extends ActionBarActivity {
	private Long startTime;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercise_start);
		startTime = System.currentTimeMillis();
		handler.removeCallbacks(updateTimer);
		handler.postDelayed(updateTimer, 1000);
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
			final TextView time = (TextView) findViewById(R.id.text);
			Long spentTime = System.currentTimeMillis() - startTime;
			Long hour = (spentTime/1000);
			Long mins = (spentTime/1000)/60;
			Long seconds = (spentTime/1000) % 60;
			time.setText(hour+":"+mins+":"+seconds);
			handler.postDelayed(this, 1000);;
		}
	};

}
