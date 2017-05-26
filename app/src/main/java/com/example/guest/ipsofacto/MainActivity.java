package com.example.guest.ipsofacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.submitLocationButton)
    Button mSubmitLocationButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.startAboutActivity) Button mStartAboutActivity;
    @Bind(R.id.startContactActivity) Button mStartContactActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSubmitLocationButton.setOnClickListener(this);
        mStartAboutActivity.setOnClickListener(this);
        mStartContactActivity.setOnClickListener(this);
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
        }
    }
}
