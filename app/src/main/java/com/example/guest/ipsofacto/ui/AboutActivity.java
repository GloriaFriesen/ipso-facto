package com.example.guest.ipsofacto.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.guest.ipsofacto.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.startContactFromAbout)
    TextView mStartContactFromAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        mStartContactFromAbout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mStartContactFromAbout) {
            Intent intent = new Intent(AboutActivity.this, ContactActivity.class);
            startActivity(intent);
        }
    }
}
