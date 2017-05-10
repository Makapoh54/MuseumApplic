package com.test.anton.museumapp.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.test.anton.museumapp.model.Item;
import com.test.anton.museumapp.model.ItemImage;

import java.util.function.Consumer;


public final class FirebaseDb {
    private static final String exhibitions = "exhibitions";
    private static final String itemImages = "item_images";
    private static final String post = "post";
    private static final String users = "users";
    private static final String creationTime = "creationTime";
    private static final String items = "items";
    private static final String collection = "collection";

/*    public static Query getTasksForUser(String assigneeKey) {
        return db().child(post).orderByChild(assignee).equalTo(assigneeKey);
    }*/

    public static void getUserByKey(String key, Consumer<DataSnapshot> onValue) {
        db().child(users).child(key).addListenerForSingleValueEvent(new OnSingleValue(onValue));
    }

    public static void getAllExhibitions(Consumer<DataSnapshot> onValue) {
        db().child(exhibitions).orderByChild(creationTime).addValueEventListener(new OnSingleValue(onValue));
    }

    public static void getItem(String uid, Consumer<DataSnapshot> onValue) {
        db().child(items).child(uid).addListenerForSingleValueEvent(new OnSingleValue(onValue));
    }

    public static void createItemImage(ItemImage newItem) {
        db().child(itemImages).push().setValue(newItem);
    }

    public static void createItem(Item newItem) {
        db().child(items).push().setValue(newItem);
    }

    public static void changePostCollection(String draggedTaskKey, String newColumn) {
        db().child(post).child(draggedTaskKey).child(collection).setValue(newColumn);
    }

    public static void deletePost(String taskKey) {
        db().child(post).child(taskKey).removeValue();
    }

   /* public static void updatePost(String taskKey, Note newNote) {
        db().child(post).child(taskKey).setValue(newNote);
    }*/

    private static DatabaseReference db() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
