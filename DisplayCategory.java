package com.example.kevindingens.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;



/**
 * Created by kevindingens on 4/24/17.
 */

public class DisplayCategory extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaycategory);
    }

    public void sendBar(View view) {
        Intent Searchintent = new Intent(DisplayCategory.this, DisplayMessageActivity.class);
        Searchintent.putExtra(EXTRA_MESSAGE, "bar");
        startActivity(Searchintent);
    }

    public void sendBakery(View view) {
        Intent Searchintent = new Intent(DisplayCategory.this, DisplayMessageActivity.class);
        Searchintent.putExtra(EXTRA_MESSAGE, "bakery");
        startActivity(Searchintent);
    }

    public void sendCafe(View view) {
        Intent Searchintent = new Intent(DisplayCategory.this, DisplayMessageActivity.class);
        Searchintent.putExtra(EXTRA_MESSAGE, "cafe");
        startActivity(Searchintent);
    }

    public void sendGrocery(View view) {
        Intent Searchintent = new Intent(DisplayCategory.this, DisplayMessageActivity.class);
        Searchintent.putExtra(EXTRA_MESSAGE, "bar");
        startActivity(Searchintent);
    }

    public void sendRestaurant(View view) {
        Intent Searchintent = new Intent(DisplayCategory.this, DisplayMessageActivity.class);
        Searchintent.putExtra(EXTRA_MESSAGE, "restaurant");
        startActivity(Searchintent);
    }

    public void sendTakeAway(View view) {
        Intent Searchintent = new Intent(DisplayCategory.this, DisplayMessageActivity.class);
        Searchintent.putExtra(EXTRA_MESSAGE, "takeaway");
        startActivity(Searchintent);
    }

    public void sendDelivery(View view) {
        Intent Searchintent = new Intent(DisplayCategory.this, DisplayMessageActivity.class);
        Searchintent.putExtra(EXTRA_MESSAGE, "delivery");
        startActivity(Searchintent);
    }

}
