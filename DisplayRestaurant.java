package com.example.kevindingens.firstapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

/**
 * Created by kevindingens on 4/29/17.
 */

public class DisplayRestaurant extends Activity {
    public static final String URL_MESSAGE = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displayrestaurant);
        String message = getIntent().getStringExtra(DisplayMessageActivity.XTRA_MESSAGE);
        String Strindex = null;
        Strindex = message.split("%")[1];
        String url = message.split("%")[0];
        String detailurl = null;
        jsonParser parse = new jsonParser();
        int index = parse.strTOint(Strindex);
        String title = null;
        String photoref = null;
        String photourl = null;
        String detailkey = null;
        String detailoutput = null;
        String rating = null;
        String pricerange = null;
        String opentext = null;
        TextView o = (TextView) findViewById(R.id.openNow);
        o.setBackgroundResource(R.drawable.grayround);
        o.setTextSize(25);
        o.setText("Currently: --");
        o.setTextColor(Color.WHITE);
        o.setGravity(Gravity.CENTER);
        try {

            String output = new getData().execute(url).get();
            Log.d("key", output);
            JSONObject js = new JSONObject(output.substring(4));
            title = parse.getTitle(js, index);
            photoref = parse.getImage(js, index);
            if (!photoref.equals("none")) {
                photourl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=" + photoref + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
                new DownloadImage((ImageView) findViewById(R.id.image1)).execute(photourl);
            } else {
                ImageView iv = (ImageView) findViewById(R.id.image1);
                iv.getLayoutParams().height = 0;
            }
            // SETTING RATING
            rating = parse.getRating(js, index);
            if (rating == null)
                rating = "-";
            // SETTING PRICE RANGE
            pricerange = parse.getPriceLevel(js, index);
            // GETTING DETAIL
            detailkey = parse.getDetailKey(js, index);
            detailurl = "https://maps.googleapis.com/maps/api/place/details/json?placeid=" + detailkey + "&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
            detailoutput = new getData().execute(detailurl).get();
            JSONObject detjs = new JSONObject(detailoutput.substring(4));
            detailsParser detpars = new detailsParser();
            // SETTING OPEN NOW
            String openNow = detpars.getOpenNow(detjs);
            if (openNow.equals("true")) {
                o.setTextSize(25);
                o.setText("Currently: OPEN");
                o.setTextColor(Color.WHITE);
                o.setBackgroundResource(R.drawable.greenround);
            } else if (openNow.equals("false")) {
                o.setTextSize(25);
                o.setText("Currently: CLOSED");
                o.setTextColor(Color.WHITE);
                o.setBackgroundResource(R.drawable.redround);
            }
            // SETTING HOURS!
            String[] ohours = detpars.getOpeningHours(detjs);
            LinearLayout linearlay = (LinearLayout) findViewById(R.id.hours);

            TextView rv = new TextView(this);
            rv.setText("        No Available Daily Hours");

            linearlay.addView(rv);
            linearlay.getLayoutParams().height = 0;

            if (ohours.length > 0) {
                rv.setText("        Daily Hours:");
                rv.setTextSize(23);
                int hinpix = 15;
                for (int h = 0; h < ohours.length; h++) {
                    TextView tv = new TextView(this);
                    tv.setText(ohours[h]);
                    hinpix = tv.getLineCount() * tv.getLineHeight();
                    tv.setHeight(70);
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextColor(Color.WHITE);
                    linearlay.addView(tv);
                }
                linearlay.getLayoutParams().height = ohours.length * 70 + 30;
            }
            // SETTING WEBSITE
            LinearLayout weblay = (LinearLayout) findViewById(R.id.weblay);
            final String website = detpars.getWebsite(detjs);
            Log.d("key", website);

            if (!website.equals("none")) {
                Button button = new Button(this);
                button.setText("Website");
                button.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent categoryIntent = new Intent(DisplayRestaurant.this, DisplayWebView.class);
                                categoryIntent.putExtra(URL_MESSAGE, website);
                                startActivity(categoryIntent);
                            }
                        }
                );
                weblay.addView(button);
            }
            // SETTING CONTACT INFO
            TextView contact = (TextView) findViewById(R.id.phone);
            TextView address = (TextView) findViewById(R.id.address);
            TextView phone = (TextView) findViewById(R.id.pnum);
            contact.setTextSize(25);
            contact.setTextColor(Color.WHITE);
            contact.setText("CONTACT INFO:");
            address.setTextSize(15);
            address.setTextColor(Color.WHITE);
            address.setText(detpars.getAddress(detjs));
            phone.setTextSize(15);
            phone.setTextColor(Color.WHITE);
            phone.setText(detpars.getPhone(detjs));



            // SETTING REVIEWS INFO
            JSONArray reviews = detpars.getReviews(detjs);
            LinearLayout ll = (LinearLayout) findViewById(R.id.contact);
            for(int ok = 0; ok < reviews.length(); ok++) {
                // Add name
                TextView revname = new TextView(this); //(TextView) findViewById(R.id.name1);
                revname.setGravity(Gravity.CENTER);
                String name = detpars.getReviewName(reviews, ok);
                revname.setText(name);
                ll.addView(revname);
                // Add Rating
                TextView revrat = new TextView(this);//(TextView) findViewById(R.id.rating1);
                revrat.setGravity(Gravity.CENTER);
                String rated = detpars.getReviewRating(reviews, ok);
                Log.d("key", rated);
                revrat.setText(rated+"/5");
                ll.addView(revrat);
                // Add text
                TextView revtext = new TextView(this);
                String rtext = detpars.getReviewText(reviews, ok);
                revtext.setText(rtext);
                ll.addView(revtext);
                //Add Spacer
                TextView space = new TextView(this);
                int id = getResources().getIdentifier("round", "drawable", getPackageName());
                Drawable drawable = getResources().getDrawable(id);
                space.setText(" ");
                space.setBackgroundColor(0xFFD4AF37);
                int length = reviews.length();
                double px = 20*(1-((double)ok/(double)length));
                int intpx = (int)px;
                Log.d("key",""+intpx);
                if(intpx == 0)
                    intpx = 45;
                Log.d("key",""+intpx);
                space.setHeight(intpx);
                ll.addView(space);

            }
            ViewGroup.LayoutParams params = ll.getLayoutParams();
            //params.height = 520 + reviews.length()*400;

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TextView t = (TextView) findViewById(R.id.text1);
        t.setTextSize(50);
        t.setTextColor(Color.WHITE);
        t.setText(Html.fromHtml("<p><strong> " + title + " </strong></p>"));
        TextView r = (TextView) findViewById(R.id.rating);
        r.setTextSize(20);
        r.setTextColor(Color.WHITE);
        r.setText(Html.fromHtml("<p>      Rating: " + rating + "</p>"));
        TextView p = (TextView) findViewById(R.id.price);
        p.setTextSize(20);
        p.setTextColor(Color.WHITE);
        p.setText(Html.fromHtml("<p>      Price Level: " + pricerange + "</p>"));
    }

    public Context getContext() {
        return context;
    }
}
