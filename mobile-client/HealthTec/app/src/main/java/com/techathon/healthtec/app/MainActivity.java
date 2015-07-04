/*
 * (c) 2015 by Harry Chan
 * All rights reserved. No part of this document may be reproduced or
 * transmitted in any form or by any means, electronic, mechanical,
 * photocopying, recording, or otherwise, without prior written
 * permission of Harry Chan.
 */
package com.techathon.healthtec.app;

import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.os.Bundle;
import android.util.Base64;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.my_button).setOnClickListener(this);
        // Get ListView object from xml
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
        listView.setAdapter(adapter);
        // listView.setBackground(R.drawable.selector);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

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

        });
    }

    public void onClick(View arg0) {
        Button b = (Button) findViewById(R.id.my_button);


        b.setClickable(false);
        new RestfulActivity().execute();
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

    private class RestfulActivity extends AsyncTask<Void, Void, String> {
        private String url;
        private String username;
        private String password;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassowrd(String password) {
            this.password = password;
        }


        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
            InputStream in = entity.getContent();

            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);
                if (n > 0) out.append(new String(b, 0, n));
            }
            return out.toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            url = "https://api-us.clusterpoint.com/100390/Sportiform";
            username = "ken.poon@dotcus.com";
            password = "12345";

            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(url);
            String text = null;
            try {
                String credentials = username + ":" + password;
                String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                httpGet.addHeader("Authorization", "Basic " + base64EncodedCredentials);
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }


            return text;
        }


        protected void onPostExecute(String results) {
            if (results != null) {
                EditText et = (EditText) findViewById(R.id.my_edit);
                et.setText(results);
            }
        }

    }
}
