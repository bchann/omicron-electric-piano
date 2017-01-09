package com.example.brian.piano;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Field;

public class BluetoothListener extends AppCompatActivity {
    int numKeys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_listener);

        Piano piano = new Piano();
        numKeys = piano.numKeys;

        for (int i = 0; i < numKeys; i++) {
            int resId = getResourceId(Integer.toString(i), "raw", getPackageName());

            piano.insertMP(i, MediaPlayer.create(this, resId));
        }
    }

    //Grabs the resource id from a string, grabbed from stackoverflow
    public int getResourceId(String pVariableName, String pResourcename, String pPackageName) {
        try {
            return getResources().getIdentifier(pVariableName, pResourcename, pPackageName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
