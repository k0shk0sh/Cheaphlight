package com.fastaccess.cheaphlight.ui.base.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Kosh on 25 May 2016, 9:09 PM
 */

public interface BaseMvp {

    interface Presenter<V> extends ValueEventListener, ChildEventListener, DatabaseReference.CompletionListener, FirebaseAuth.AuthStateListener {

        void attachView(V view);

        @NonNull FirebaseAuth getFirebaseAuth();

        @NonNull FirebaseDatabase getFirebaseDatabase();

        @NonNull DatabaseReference getDatabaseReference(@NonNull String name);

        @Nullable FirebaseUser getUser();

        void onDestroy();
    }
}
