package com.example.guest.ipsofacto.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.ipsofacto.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String [] states = new String[] {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"
    };

    @Bind(R.id.submitLocationButton)
    Button mSubmitLocationButton;
    @Bind(R.id.startAboutActivity) Button mStartAboutActivity;
    @Bind(R.id.startContactActivity) Button mStartContactActivity;
    @Bind(R.id.stateTextView) AutoCompleteTextView mStateTextView;
    @Bind(R.id.titleTextView) TextView mTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface sunlightFont = Typeface.createFromAsset(getAssets(), "fonts/sunlight.ttf");
        mTitleTextView.setTypeface(sunlightFont);

        mSubmitLocationButton.setOnClickListener(this);
        mStartAboutActivity.setOnClickListener(this);
        mStartContactActivity.setOnClickListener(this);
        mStateTextView.getOnItemSelectedListener();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states);
        mStateTextView.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        if(v == mSubmitLocationButton) {
            if (isInternetAvailable()) {
                String state = mStateTextView.getText().toString();
                if (state.length() == 0) {
                    mStateTextView.setError("Please select a state.");
                } else {
                    Intent intent = new Intent(MainActivity.this, LegislatorListActivity.class);
                    intent.putExtra("state", state);
                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Please check your internet connection and try again.", Toast.LENGTH_LONG).show();
            }
        } else if (v == mStartAboutActivity) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mStartContactActivity) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
