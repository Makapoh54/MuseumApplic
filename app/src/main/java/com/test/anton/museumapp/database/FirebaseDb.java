package com.test.anton.museumapp.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.anton.museumapp.model.Item;

import java.util.function.Consumer;


public final class FirebaseDb {
    private static final String exhibitions = "exhibitions";
    private static final String itemImages = "item_images";
    private static final String creationTime = "creationTime";
    private static final String items = "items";

    public static void getAllExhibitions(Consumer<DataSnapshot> onValue) {
        db().child(exhibitions).orderByChild(creationTime).addValueEventListener(new OnSingleValue(onValue));
    }

    public static void getItem(String uid, Consumer<DataSnapshot> onValue) {
        db().child(items).child(uid).addListenerForSingleValueEvent(new OnSingleValue(onValue));
    }

    public static void createItem(Item newItem) {
        db().child(items).push().setValue(newItem);
    }

    private static DatabaseReference db() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
