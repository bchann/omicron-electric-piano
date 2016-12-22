package com.example.brian.piano;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private Button applyButton, resetButton, connectButton;
    private Spinner soundSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        applyButton = (Button) findViewById(R.id.applyButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        connectButton = (Button) findViewById(R.id.connectButton);
        soundSpinner = (Spinner) findViewById(R.id.spinner);

        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connect(v);
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sounds_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        soundSpinner.setAdapter(adapter);
    }

    //Turns on the bluetooth adapter for the device
    private void turnOn(View v) {
        bluetoothAdapter.enable();
    }

    //Turns off the bluetooth adapter for the device
    private void turnOff(View v) {
        bluetoothAdapter.disable();
    }

    private void connect(View v) {
        turnOn(v);
        startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
        Toast.makeText(getApplicationContext(), "Select device", Toast.LENGTH_LONG).show();
        System.err.println(bluetoothAdapter.getBondedDevices());
    }
}
