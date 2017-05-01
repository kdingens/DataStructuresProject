public class DisplayMessageActivity extends AppCompatActivity {
    public static final String XTRA_MESSAGE = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dislayresults);
        String message = getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE);
        String title = null;
        String camplat = "41.6764";
        String camplon = "-86.2520";
        String towncoords = "41.694782,-86.218183";
        String bendcoords = "41.679724,-86.251214";
        String rowcoords = "41.6977797,-86.2300138";
        String urlstring = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+bendcoords+"&radius=500&type=food&keyword="+message+"&key=AIzaSyDkg2wJq0Pq94JFmYncY4pwyEMgHjuuEd0";
        String output = null;
        ScrollView sview = (ScrollView) findViewById(R.id.scroll1);
        LinearLayout linlay = (LinearLayout) findViewById(R.id.linear1);
        //TextView tv = (TextView) findViewById(R.id.searchtext);
        //tv.setText("You searched South Bend for: \""+message+"\"");
        try {
            output = new getData().execute(urlstring).get();
            Log.d("trying", output.substring(4));
            JSONObject js = new JSONObject(output.substring(4));
            jsonParser parse = new jsonParser();
            int size = parse.getSize(js);
            for (int i = 0; i < size; i++){
                title = parse.getTitle(js,i);
                Button button = new Button(this);
                button.setText(title);
                button.setTag(urlstring+"%"+i);
                button.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent categoryIntent = new Intent(DisplayMessageActivity.this, DisplayRestaurant.class);
                                String message = v.getTag().toString();
                                categoryIntent.putExtra(XTRA_MESSAGE, message);
                                startActivity(categoryIntent);

                            }
                        }
                );
                linlay.addView(button);
            }
            title = parse.getTitle(js,0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException f) {
            f.printStackTrace();
        } catch (JSONException g){
            g.printStackTrace();
        }
    }
}
