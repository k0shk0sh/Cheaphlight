package com.fastaccess.cheaphlight.helper;

import android.support.annotation.NonNull;

import com.fastaccess.cheaphlight.App;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kosh on 29 May 2016, 5:09 AM
 */

public class FirebaseHelper {

    public static void removeEventListener(@NonNull DatabaseReference reference, @NonNull ValueEventListener listener) {
        try {
            reference.removeEventListener(listener);
        } catch (Exception ignored) {}
    }

    public static void removeChildListener(@NonNull DatabaseReference reference, @NonNull ChildEventListener listener) {
        try {
            reference.removeEventListener(listener);
        } catch (Exception ignored) {}
    }

    public static void removeAuthListener(@NonNull FirebaseAuth auth, @NonNull FirebaseAuth.AuthStateListener listener) {
        try {
            auth.removeAuthStateListener(listener);
        } catch (Exception ignored) {}
    }

    public static <T> T getObject(String json, Class<T> clazz) {
        return App.gson().fromJson(json, clazz);
    }

    public static <T> List<T> getList(String json, Class<T[]> clazz) {
        return Arrays.asList(App.gson().fromJson(json, clazz));
    }

}
