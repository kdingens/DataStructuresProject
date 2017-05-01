public class jsonParser {
    public String getTitle (JSONObject a, int b) throws JSONException {
        JSONArray results = a.getJSONArray("results");
        if(b < results.length()){
            return results.getJSONObject(b).getString("name");
        }
        else{
            return results.getJSONObject(results.length()-1).getString("name");
        }
    }

    public String getImage (JSONObject a, int b) throws JSONException{
        JSONArray results = a.getJSONArray("results");
        JSONArray photos = results.getJSONObject(b).getJSONArray("photos");
        String photoref = photos.getJSONObject(0).getString("photo_reference");
        return photoref;
    }

    public int getSize(JSONObject a) throws JSONException {
        JSONArray res = a.getJSONArray("results");
        return res.length();
    }

    public int strTOint(String a) {
        if(a.equals("0"))
            return 0;
        else if(a.equals("1"))
            return 1;
        else if(a.equals("2"))
            return 2;
        else if(a.equals("3"))
            return 3;
        else if(a.equals("4"))
            return 4;
        else if(a.equals("5"))
            return 5;
        else if(a.equals("6"))
            return 6;
        else if(a.equals("7"))
            return 7;
        else if(a.equals("8"))
            return 8;
        else if(a.equals("9"))
            return 9;
        else if(a.equals("10"))
            return 10;
        else if(a.equals("11"))
            return 11;
        else if(a.equals("12"))
            return 12;
        else if(a.equals("13"))
            return 13;
        else if(a.equals("14"))
            return 14;
        else if(a.equals("15"))
            return 15;
        else if(a.equals("16"))
            return 16;
        else if(a.equals("17"))
            return 17;
        else if(a.equals("18"))
            return 18;
        else if(a.equals("19"))
            return 19;
        else if(a.equals("20"))
            return 20;
        else if(a.equals("21"))
            return 21;
        else
            return 0;
    }
}
