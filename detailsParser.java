package com.example.kevindingens.firstapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kevindingens on 5/2/17.
 */

public class detailsParser {
    public String getOpenNow(JSONObject js) {
        try {


            JSONObject results = js.getJSONObject("result");
            Log.d("color", "inside");
            JSONObject openinghours = results.getJSONObject("opening_hours");
            Log.d("color", "inside");
            String openNow = openinghours.getString("open_now");
            Log.d("color", "inside");
            return openNow;
        }catch (Exception e) {
            Log.d("color", "exception");
            return "None";
        }
    }

   public String getWebsite (JSONObject js) throws JSONException{
        JSONObject result = js.getJSONObject("result");
        if(result.has("website")){
            Log.d("key", result.getString("website"));
            return result.getString("website");
        }
        else
           return "none";
    }

    public JSONArray getReviews(JSONObject js) throws JSONException{
        JSONObject result = js.getJSONObject("result");
        Log.d("try","1hello");
        if(result.has("reviews")){
            Log.d("try","1hello");
            return result.getJSONArray("reviews");
        }
        else
            return null;
    }

    public String getReviewName(JSONArray js, int b) throws JSONException{

        JSONObject review = js.getJSONObject(b);
        if(review.has("author_name"))
            return review.getString("author_name");
        else
            return "--";
    }

    public String getReviewRating(JSONArray js, int b) throws JSONException{
        JSONObject review = js.getJSONObject(b);
        if(review.has("rating"))
            return review.getString("rating");
        else
            return "--";
    }

    public String getReviewText(JSONArray js, int b) throws JSONException{
        JSONObject review = js.getJSONObject(b);
        if(review.has("text"))
            return review.getString("text");
        else
            return "--";
    }

    public String getPhone(JSONObject js) throws JSONException{
        JSONObject result = js.getJSONObject("result");
        if(result.has("formatted_phone_number")){
            return result.getString("formatted_phone_number");
        }
        else
            return "none";
    }

    public String getAddress (JSONObject js) throws JSONException{
        JSONObject result = js.getJSONObject("result");
        if(result.has("formatted_address"))
            return result.getString("formatted_address");
        else
            return "none";
    }

    public String[] getOpeningHours(JSONObject js) throws JSONException{
        JSONObject oh = js.getJSONObject("result").getJSONObject("opening_hours");
        JSONArray arrJson = oh.getJSONArray("weekday_text");
        String[] hours = new String[arrJson.length()];
        for(int i = 0; i < arrJson.length(); i++){
            hours[i] = arrJson.getString(i);
        }
        return hours;
    }

}
