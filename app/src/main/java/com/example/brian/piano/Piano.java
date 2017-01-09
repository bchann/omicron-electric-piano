package com.example.brian.piano;

import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Brian Chan on 1/6/2017.
 */

public class Piano {

    public final int numKeys = 39;
    private int prevState[] = new int[numKeys];
    private int currState[] = new int[numKeys];
    private MediaPlayer notes[] = new MediaPlayer[numKeys];

    public Piano() {}

    //Parses new string
    public void parseString(String input) {
        prevState = currState;

        for (int i = 0; i < input.length(); i++) {
            currState[i] = Character.getNumericValue(input.charAt(i));

            try {
                checkKey(i);
            } catch (IOException e) {
                System.err.println("ERROR: parseString()");
                e.printStackTrace();
            }
        }
    }

    //Checks if the key should change states
    private void checkKey(int key) throws IOException {
        if (prevState[key] == 0 && currState[key] == 1 ) {
            playNote(key);
        }
        else if (prevState[key] == 1 && currState[key] == 0) {
            if (notes[key].isPlaying()) {
                stopNote(key);
            }
        }
    }

    //Inserts MediaPlayers for all the keys, has to be done in activity
    public void insertMP(int key, MediaPlayer mp) {
        notes[key] = mp;
    }

    private void playNote(int key) {
        //error but works, needs to copy to activity
        notes[key].start();
    }

    private void stopNote(int key) throws IOException {
        notes[key].stop();
        notes[key].prepare();
    }
}
