package com.example.brian.piano;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class CoverActivity extends AppCompatActivity {
    private MediaPlayer mp;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            System.err.println("ERROR: Device does not support Bluetooth");
        }

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                mDevice = device;
            }
        }

        Button helpButton = (Button) findViewById(R.id.aboutButton);

        mp = MediaPlayer.create(this, R.raw.pianotest);

        helpButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //How to use fragment
    public void help(View v) {}
}
