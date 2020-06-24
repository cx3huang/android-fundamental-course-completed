package com.example.implicitintents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // 1. create EditText object for website URI
    // 2. get a reference to the EditText instance (once, in onCreate())
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);

        // 3. create a new Intent object, Intent.ACTION_VIEW as the action, URI as data
        // 4. make sure the Intent can be handled by an activity
        // 5. start the activity
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        startActivity(intent);

        if(intent.resolveActivity(getPackageManager()) == null) {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
        /*else {
            startActivity(intent);
        }*/
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        startActivity(intent);

        if(intent.resolveActivity(getPackageManager()) == null ) {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
        /*else {
            startActivity(intent);
        } */
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }
}