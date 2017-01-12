package com.example.brian.piano;

import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by Brian Chan on 1/6/2017.
 */

public class Piano {

    public final int numKeys = 38;
    private int prevState[] = new int[numKeys];
    private int currState[] = new int[numKeys];
    private MediaPlayer notes[] = new MediaPlayer[numKeys];

    public Piano() {}

    //Parses new string
    public void parseString(String input) {
        //System.err.println("len: "+ input.length());
        if (input.length() == numKeys + 1) {
            for (int i = 0; i < numKeys; i++) {
                int val = Character.getNumericValue(input.charAt(i));

                if (currState[i] == 1) {
                    playNote(i);
                    System.err.println("i: " + i);
                    System.err.println("prev: " + prevState[i]);
                    System.err.println("curr: " + currState[i]);
                }

                try {
                    checkKey(val, i);
                } catch (IOException e) {
                    System.err.println("ERROR: parseString()");
                    e.printStackTrace();
                }
            }
        }
    }

    //Checks if the key should change states
    private void checkKey(int val, int key) throws IOException {
        if (val == 1 && !notes[key].isPlaying()) {
            playNote(key);
        }
        else if (val == 0 && notes[key].isPlaying()) {
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
