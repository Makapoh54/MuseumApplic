package com.test.anton.museumapp.database;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

import timber.log.Timber;

class OnSingleValue implements ValueEventListener {

    private final Consumer<DataSnapshot> onValue;

    OnSingleValue(Consumer<DataSnapshot> onValue) {
        this.onValue = onValue;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        onValue.accept(dataSnapshot);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Timber.e("DB ERROR" + databaseError.getMessage());
    }
}

