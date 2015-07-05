package com.techathon.healthtec.util;

import android.os.AsyncTask;
import android.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Paptimus on 4/7/2015.
 */
public class RestfulPutActivity extends AsyncTask<String, Void, String> {
    private String url;
    private String username;
    private String password;
    private String postObject;

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

    public String getPostObject() {
        return this.postObject;
    }

    public void setPostObject(String postObject) {
        this.postObject = postObject;
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

    //for parameteres -> URL, username, password, JSON string
    @Override
    protected String doInBackground(String... params) {
        try {
            url = params[0];
            username = params[1];
            password = params[2];
            postObject = params[3];
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpPut httpPut = new HttpPut(url);
        String text = null;
        try {
            if(username != null && !username.equals("") && password != null) {
                String credentials = username + ":" + password;
                String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                httpPut.addHeader("Authorization", "Basic " + base64EncodedCredentials);
            }
            httpPut.setEntity(new StringEntity(postObject, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPut, localContext);
            HttpEntity entity = response.getEntity();
            text = getASCIIContentFromEntity(entity);
        } catch (Exception e) {
            return e.getLocalizedMessage();
        }


        return text;
    }
}