package com.example.guest.ipsofacto.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.ipsofacto.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    Map<String, String> states = new HashMap<>();
    String chamber;

    @Bind(R.id.submitLocationButton) Button mSubmitLocationButton;
    @Bind(R.id.startAboutActivity) Button mStartAboutActivity;
    @Bind(R.id.startContactActivity) Button mStartContactActivity;
    @Bind(R.id.stateTextView) AutoCompleteTextView mStateTextView;
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.radioGroup) RadioGroup mRadioGroup;

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
        mRadioGroup.setOnCheckedChangeListener(this);

        states = processStates();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states.keySet().toArray());
        mStateTextView.setAdapter(adapter);
        mStateTextView.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == mSubmitLocationButton) {
            if (isInternetAvailable()) {
                String state = mStateTextView.getText().toString();
                if (state.length() == 0) {
                    Toast.makeText(this, "Please select a state.", Toast.LENGTH_SHORT).show();
                } else if (chamber == null) {
                    Toast.makeText(this, "Please select a chamber.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, LegislatorListActivity.class);
                    intent.putExtra("state", states.get(state));
                    intent.putExtra("chamber", chamber);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        chamber = radioGroup.findViewById(i).getTag().toString();
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private Map<String, String> processStates() {
        String[] stateNames = getResources().getStringArray(R.array.states);
        String[] stateAbbreviations = getResources().getStringArray(R.array.abbreviatedStates);
        for (int i = 0; i < stateNames.length; i++) {
            states.put(stateNames[i], stateAbbreviations[i]);
        }
        return states;
    }
}
