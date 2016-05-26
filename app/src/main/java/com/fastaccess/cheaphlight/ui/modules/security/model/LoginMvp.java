package com.fastaccess.cheaphlight.ui.modules.security.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Kosh on 26 May 2016, 6:47 PM
 */

public interface LoginMvp {

    interface View {

        void showProgress();

        void hideProgress();

        void onGoogleLoginSuccessfully(@NonNull GoogleSignInResult result);

        void onGoogleLogin();

        void showMessage(String errorMessage);

        void showMessage(@StringRes int msgId);

        void onSuccessfullyLoggedIn();
    }

    interface Presenter extends GoogleApiClient.OnConnectionFailedListener, FirebaseAuth.AuthStateListener, OnCompleteListener<AuthResult> {

        void onGoogleLogin(@NonNull Activity context);

        void onFacebookLogin(@NonNull LoginButton loginButton);

        @NonNull FirebaseAuth getFirebaseAuth();

        @NonNull GoogleSignInOptions googleSignInOptions(@NonNull Context context);

        @NonNull GoogleApiClient getGoogleApiClient(@NonNull Activity context);

        void onActivityResult(int requestCode, int resultCode, Intent data);

        void handleGoogleLogin(@NonNull Activity context, @NonNull GoogleSignInResult result);

        void onStart();

        void onStop();

        void onFinish(@NonNull Activity activity);
    }

}
