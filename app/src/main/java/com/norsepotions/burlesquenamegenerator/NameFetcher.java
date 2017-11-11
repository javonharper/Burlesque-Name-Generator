package com.norsepotions.burlesquenamegenerator;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NameFetcher {
    private static final String TAG = NameFetcher.class.getSimpleName();

    List<String> mFirstNames;
    List<String> mLastNames;
    Firebase mFirebase;

    public NameFetcher() {
        mFirebase = new Firebase("http://burlesque-name-gen.firebaseio.com/");
        initNames();
    }

    public String getName() {
        String firstName = mFirstNames.get(new Random().nextInt(mFirstNames.size()));
        String lastName = mLastNames.get(new Random().nextInt(mLastNames.size()));
        return firstName + " " + lastName;
    }

    private void initNames() {
        initPlaceholderNames();

        mFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mFirstNames = (List<String>) snapshot.child("first_names").getValue();
                mLastNames = (List<String>) snapshot.child("last_names").getValue();
            }

            @Override
            public void onCancelled(FirebaseError error) {
                Log.e(TAG, "The read failed: " + error.getMessage());
            }
        });
    }

    private void initPlaceholderNames() {
        mFirstNames = Arrays.asList(new String[]{"Denise", "Cynthia", "Roslind"});
        mLastNames = Arrays.asList(new String[]{"St. Cyr", "Darling", "von Teese"});
    }
}