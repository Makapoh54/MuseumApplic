package com.test.anton.museumapp.database;

import com.google.firebase.database.DataSnapshot;
import com.test.anton.museumapp.model.Exhibition;

import java.util.ArrayList;

public final class Snapshot {

    public static ArrayList<Exhibition> toExhibitions(DataSnapshot dataSnapshot) {
        ArrayList<Exhibition> exhibitionsList = new ArrayList<>();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            exhibitionsList.add(snapshot.getValue(Exhibition.class));
        }
        return exhibitionsList;
    }
}
