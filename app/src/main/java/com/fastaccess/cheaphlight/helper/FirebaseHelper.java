package com.fastaccess.cheaphlight.helper;

import android.support.annotation.NonNull;

import com.fastaccess.cheaphlight.App;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Kosh on 29 May 2016, 5:09 AM
 */

public class FirebaseHelper {

    public static void removeCompletionListener(@NonNull DatabaseReference.CompletionListener listener) {
        try {
            App.getInstance().getFirebaseDatabase().getReference().removeValue(listener);
        } catch (Exception ignored) {}
    }

    public static void removeEventListener(@NonNull ValueEventListener listener) {
        try {
            App.getInstance().getFirebaseDatabase().getReference().removeEventListener(listener);
        } catch (Exception ignored) {}
    }

    public static void removeChildListener(@NonNull ChildEventListener listener) {
        try {
            App.getInstance().getFirebaseDatabase().getReference().removeEventListener(listener);
        } catch (Exception ignored) {}
    }

    public static void removeAuthListener(@NonNull FirebaseAuth.AuthStateListener listener) {
        try {
            App.getInstance().getFirebaseAuth().removeAuthStateListener(listener);
        } catch (Exception ignored) {}
    }
}
