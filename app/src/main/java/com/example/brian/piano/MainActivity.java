package com.example.brian.piano;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private Button applyButton, resetButton, connectButton;
    private Spinner soundSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        applyButton = (Button) findViewById(R.id.applyButton);
        resetButton = (Button) findViewById(R.id.resetButton);
        connectButton = (Button) findViewById(R.id.connectButton);
        soundSpinner = (Spinner) findViewById(R.id.spinner);

        connectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //connect(v);
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sounds_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        soundSpinner.setAdapter(adapter);

        applyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String sound = soundSpinner.getSelectedItem().toString();

                Snackbar.make(v, "Piano sound set to " + sound, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                soundSpinner.setSelection(0);

                Snackbar.make(v, "Piano sound reset", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //Toast.makeText(getApplicationContext(), "Piano sound reset",
                //        Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Turns on the bluetooth adapter for the device
    private void turnOn(View v) {
        bluetoothAdapter.enable();
    }

    //Turns off the bluetooth adapter for the device
    private void turnOff(View v) {
        bluetoothAdapter.disable();
    }

    //Turns on bluetooth and brings user to the bluetooth settings screen
    private void connect(View v) {
        turnOn(v);
        startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
        Toast.makeText(getApplicationContext(), "Select device", Toast.LENGTH_LONG).show();
        System.err.println(bluetoothAdapter.getBondedDevices());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
