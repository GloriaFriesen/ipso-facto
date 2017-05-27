package com.example.guest.ipsofacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String [] states = new String[] {
            "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming", "District of Columbia"
    };

    @Bind(R.id.submitLocationButton)
    Button mSubmitLocationButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.startAboutActivity) Button mStartAboutActivity;
    @Bind(R.id.startContactActivity) Button mStartContactActivity;
    @Bind(R.id.stateTextView) AutoCompleteTextView mStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitLocationButton.setOnClickListener(this);
        mStartAboutActivity.setOnClickListener(this);
        mStartContactActivity.setOnClickListener(this);
        mStateTextView.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states);
        mStateTextView.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
        if(v == mSubmitLocationButton) {
            String location = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, LegislatorListActivity.class);
            intent.putExtra("location", location);
            startActivity(intent);
        } else if (v == mStartAboutActivity) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mStartContactActivity) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        } else if (v == mStateTextView) {
            String state = "Oregon";
            Intent intent = new Intent(MainActivity.this, LegislatorListActivity.class);
            intent.putExtra("state", state);
            startActivity(intent);
        }
    }
}
