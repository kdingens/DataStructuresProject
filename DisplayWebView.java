package com.example.kevindingens.firstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.webkit.WebView;

/**
 * Created by kevindingens on 5/4/17.
 */

public class DisplayWebView extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaywebview);
        String url = getIntent().getStringExtra(DisplayRestaurant.URL_MESSAGE);
        WebView wv = (WebView) findViewById(R.id.wv1);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }
}
