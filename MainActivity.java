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
        Intent popularIntent = new Intent(this, DisplayPopular.class);
        startActivity(popularIntent);
    }

    public void displayRecommended(View view) {
        Intent categoryIntent = new Intent(this, DisplayRecommended.class);
        startActivity(categoryIntent);
    }

    public void displayOnCampus(View view) {
        Intent categoryIntent = new Intent(this, DisplayOnCampus.class);
        startActivity(categoryIntent);
    }

}
