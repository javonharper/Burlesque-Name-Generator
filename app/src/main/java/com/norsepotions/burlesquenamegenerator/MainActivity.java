package com.norsepotions.burlesquenamegenerator;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Bind(R.id.nameView) TextView mNameView;
    @Bind(R.id.shareImageView) ImageView mShareImageView;
    @Bind(R.id.newNameButton) Button mNewNameButton;


    NameFetcher mNameFetcher;
    String mLastGeneratedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        attachListeners();

        mNameFetcher = new NameFetcher();

        generateNewName();
    }

    @OnClick(R.id.newNameButton)
    public void generateNewName() {
        String newName = mNameFetcher.getName();
        mNameView.setText(newName);

        mLastGeneratedName = newName;
    }

    @OnClick(R.id.shareImageView)
    public void shareName() {
        String body = "My new burlesque name is " + mLastGeneratedName +".\n\n via Burlesque Name Generator for Android";
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        sendIntent.putExtra(Intent.EXTRA_TEXT, body);

        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_via)));

        mShareImageView.setBackgroundColor(0x00000000);
    }

    public void attachListeners() {
        mShareImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mShareImageView.setBackgroundColor(Color.parseColor("#000000"));
                mShareImageView.getBackground().setAlpha(20);

                return false;
            }
        });

        mNewNameButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mNewNameButton.getBackground().setAlpha(180);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mNewNameButton.getBackground().setAlpha(255);
                }

                return false;
            }
        });
    }
}
