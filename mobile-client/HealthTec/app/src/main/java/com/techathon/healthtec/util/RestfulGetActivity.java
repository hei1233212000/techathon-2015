package com.techathon.healthtec.util;

import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Paptimus on 4/7/2015.
 */
 public class RestfulGetActivity extends AsyncTask<String, Void, String> {
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

    public void setPassword(String password) {
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

    //for parameteres -> URL, username, password
    @Override
    protected String doInBackground(String... params) {
        try {
            url = params[0];
            username = params[1];
            password = params[2];
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(url);
        String text = null;
        try {
            if(username != null && !username.equals("") && password != null) {
                String credentials = username + ":" + password;
                String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                httpGet.addHeader("Authorization", "Basic " + base64EncodedCredentials);
            }
            HttpResponse response = httpClient.execute(httpGet, localContext);
            HttpEntity entity = response.getEntity();
            text = getASCIIContentFromEntity(entity);
            Log.e("GET: exercises", text);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }


        return text;
    }

}
