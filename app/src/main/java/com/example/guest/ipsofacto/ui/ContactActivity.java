package com.example.guest.ipsofacto.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.guest.ipsofacto.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.emailTextView) TextView mEmailTextView;
    @BindView(R.id.linkedinTextView) TextView mLinkedinTextView;
    @BindView(R.id.githubTextView) TextView mGithubTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

        mEmailTextView.setOnClickListener(this);
        mLinkedinTextView.setOnClickListener(this);
        mGithubTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mEmailTextView) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, "gloriafriesen@gmail.com");
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            }
        }
        if (v == mLinkedinTextView) {
            Uri webpage = Uri.parse("https://www.linkedin.com/in/gloriafriesen");
            Intent linkedinIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(linkedinIntent);
        }
        if (v == mGithubTextView) {
            Uri webpage = Uri.parse("https://www.github.com/gloriafriesen");
            Intent githubIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(githubIntent);
        }
    }
}
