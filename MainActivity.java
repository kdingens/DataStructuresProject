package com.example.kevindingens.firstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
        Intent Searchintent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        Searchintent.putExtra(EXTRA_MESSAGE, message);
        startActivity(Searchintent);
    }

    public void displayCategories(View view) {
        Intent categoryIntent = new Intent(this, DisplayCategory.class);
        startActivity(categoryIntent);
    }

    public void displayPopular(View view) {
        Intent popularIntent = new Intent(this, DisplayMessageActivity.class);
        popularIntent.putExtra(EXTRA_MESSAGE, "pop");
        startActivity(popularIntent);
    }

    public void displayRecommended(View view) {
        Intent categoryIntent = new Intent(this, DisplayMessageActivity.class);
        categoryIntent.putExtra(EXTRA_MESSAGE, "rec");
        startActivity(categoryIntent);
    }

    public void displayOnCampus(View view) {
        Intent categoryIntent = new Intent(this, DisplayMessageActivity.class);
        categoryIntent.putExtra(EXTRA_MESSAGE, "OnCampus");
        startActivity(categoryIntent);
    }

}
