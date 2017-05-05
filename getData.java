package com.example.kevindingens.firstapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kevindingens on 4/28/17.
 */

public class getData extends AsyncTask<String, Void, String> {
    protected String doInBackground(String... url1){
        URL url = null;
        try {
            url = new URL(url1[0]);
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        }
        String line = null;
        String result = null;
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            line = in.readLine();
            while(line != null){
                result = result+line;
                line = in.readLine();
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        } finally {
            urlConnection.disconnect();
            return result;
        }
    }

    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }
}
