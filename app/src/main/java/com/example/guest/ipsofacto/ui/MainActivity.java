package com.example.guest.ipsofacto.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {
    Map<String, String> states = new HashMap<>();
    String chamber;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Bind(R.id.submitLocationButton) Button mSubmitLocationButton;
    @Bind(R.id.stateTextView) AutoCompleteTextView mStateTextView;
    @Bind(R.id.titleTextView) TextView mTitleTextView;
    @Bind(R.id.radioGroup) RadioGroup mRadioGroup;
    @Bind(R.id.savedLegislatorButton) Button mSavedLegislatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + ".");
                } else {

                }
            }
        };

        Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/juice.ttf");
        mTitleTextView.setTypeface(titleFont);

        mSubmitLocationButton.setOnClickListener(this);
        mStateTextView.getOnItemSelectedListener();
        mRadioGroup.setOnCheckedChangeListener(this);

        states = processStates();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, states.keySet().toArray());
        mStateTextView.setAdapter(adapter);
        mStateTextView.setOnItemClickListener(this);
        mSavedLegislatorButton.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
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
        } else if (v == mSavedLegislatorButton) {
            Intent intent = new Intent(MainActivity.this, SavedLegislatorListActivity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        if (id == R.id.action_contact) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
