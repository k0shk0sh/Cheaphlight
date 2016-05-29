package com.fastaccess.cheaphlight.ui.base.mvp.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.helper.FirebaseHelper;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.ui.base.mvp.BaseMvp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Kosh on 25 May 2016, 9:12 PM
 */

public class BasePresenter<V> implements BaseMvp.Presenter<V> {

    private V view;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private BasePresenter() {throw new IllegalStateException("Cant not be initialized");}

    protected BasePresenter(V view) {
        attachView(view);
        Logger.e(view.getClass());
    }

    @Override public void attachView(V view) {
        this.view = view;
    }

    @Override public void onDestroy() {
        if (firebaseAuth != null) {
            FirebaseHelper.removeAuthListener(firebaseAuth, this);
        }
        if (databaseReference != null) {
            FirebaseHelper.removeChildListener(databaseReference, this);
            FirebaseHelper.removeEventListener(databaseReference, this);
        }
    }

    @NonNull @Override public FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) firebaseAuth = FirebaseAuth.getInstance();
        return firebaseAuth;
    }

    @NonNull @Override public FirebaseDatabase getFirebaseDatabase() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        return firebaseDatabase;
    }

    @NonNull @Override public DatabaseReference getDatabaseReference(@NonNull String name) {
        if (databaseReference == null) databaseReference = getFirebaseDatabase().getReference(name);
        return databaseReference;
    }

    @Nullable @Override public FirebaseUser getUser() {
        return getFirebaseAuth().getCurrentUser();
    }

    protected boolean isViewAttached() {
        return view != null;
    }

    protected V getView() {
        checkViewAttached();//ensure checking, avoid doing it everytime.
        return view;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) throw new NullPointerException("View is not injected to presenter");
    }

    protected App getApp() {
        return App.getInstance();
    }

    @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

    }

    @Override public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override public void onCancelled(DatabaseError databaseError) {

    }

    @Override public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {}
}
