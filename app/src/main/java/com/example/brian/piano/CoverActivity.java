package com.example.brian.piano;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
    }

    //Send to tone selection screen
    public void begin(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //How to use fragment
    public void help(View v) { }

}
