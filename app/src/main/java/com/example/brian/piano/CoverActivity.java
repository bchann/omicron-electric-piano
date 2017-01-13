package com.example.brian.piano;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class CoverActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        Button helpButton = (Button) findViewById(R.id.aboutButton);

        mp = MediaPlayer.create(this, R.raw.pianotest1);

        helpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mp.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    mp.stop();

                    try {
                        mp.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return true;
            }
        });
    }

    //Send to tone selection screen
    public void begin(View v) {
        Intent intent = new Intent(this, BluetoothListener.class);
        startActivity(intent);
    }

    //How to use fragment
    public void help(View v) {}
}
