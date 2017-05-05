package com.example.kevindingens.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;


public class DisplayMessageActivity extends AppCompatActivity {
    public static final String XTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        if(message.equals("pop")){
            setContentView(R.layout.displaypopular);
            //String bendcoords = "41.679724,-86.251214";
            //String urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=10000&type=food&keyword=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        }
        else if (message.equals("rec")){
            setContentView(R.layout.displayrecommended);
            //String bendcoords = "41.679724,-86.251214";
            //String urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=10000&type=food&keyword=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        }
        else {
            setContentView(R.layout.dislayresults);
            String title = null;
            String campcoords = "41.7056,-86.2353";
            String towncoords = "41.694782,-86.218183";
            String bendcoords = "41.679724,-86.251214";
            String rowcoords = "41.6977797,-86.2300138";
            String urlstring = null;
            if (message.equals("bar"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=500&type=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else if (message.equals("bakery"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=500&type=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else if (message.equals("cafe"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=500&type=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else if (message.equals("grocery"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=500&type=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else if (message.equals("takeaway"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=500&type=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else if (message.equals("restaurant"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=500&type=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else if (message.equals("delivery"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=500&type=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else if (message.equals("OnCampus"))
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + campcoords + "&radius=500&type=food&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            else
                urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + bendcoords + "&radius=10000&type=food&keyword=" + message + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            String output = null;
            Log.d("trying", urlstring);
            //ScrollView sview = (ScrollView) findViewById(R.id.scroll1);
            LinearLayout linlay = (LinearLayout) findViewById(R.id.linear1);
            try {
                output = new getData().execute(urlstring).get();
                Log.d("trying", output.substring(4));
                JSONObject js = new JSONObject(output.substring(4));
                jsonParser parse = new jsonParser();
                int size = parse.getSize(js);
                for (int i = 0; i < size; i++) {
                    title = parse.getTitle(js, i);
                    Button button = new Button(this);
                    button.setText(title);
                    button.setTag(urlstring + "%" + i);
                    button.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent categoryIntent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
                                    String message = v.getTag().toString();
                                    Log.d("trying", "TAG"+message);
                                    categoryIntent.putExtra(XTRA_MESSAGE, message);
                                    startActivity(categoryIntent);

                                }
                            }
                    );
                    linlay.addView(button);
                }
                //title = parse.getTitle(js, 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException f) {
                f.printStackTrace();
            } catch (JSONException g) {
                g.printStackTrace();
            }

        }
    }
    public void sendIchiban(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "ichiban";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendCambodian(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "cambodian";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendPits(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "pits";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendEmporium(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "emporium";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendMango(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "mango";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendTippecanoe(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "tippecanoe";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendTaphouse(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "taphouse";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendCarmelas(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "carmelas";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }

    public void sendChipotle(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "chipotle";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }
    public void sendRoccos(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "roccos";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }
    public void sendBlaze(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "blaze";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }
    public void sendFive(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "five";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }
    public void sendNicks(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "nicks";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }
    public void sendSimply(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "simply";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }
    public void sendSalsas(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "salsas";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }
    public void sendEvil(View view) {
        Intent Searchintent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
        String keyword = "evil";
        int i = 0;
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.679724,-86.251214&radius=10000&type=food&keyword="+keyword+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        Searchintent.putExtra(XTRA_MESSAGE, url+"%"+i);
        startActivity(Searchintent);
    }


}
