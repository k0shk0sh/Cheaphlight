package com.fastaccess.cheaphlight.ui.modules.security.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.facebook.login.widget.LoginButton;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.helper.PrefConstance;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.main.view.MainView;
import com.fastaccess.cheaphlight.ui.modules.security.model.LoginMvp;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by Kosh on 26 May 2016, 6:50 PM
 */

public class LoginPresenter extends BasePresenter<LoginMvp.View> implements LoginMvp.Presenter {

    private final static int GOOGLE_LOGIN_REQUEST_CODE = 1;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth mAuth;

    private LoginPresenter(LoginMvp.View view) {
        super(view);
    }

    public static LoginPresenter with(LoginMvp.View view) {
        return new LoginPresenter(view);
    }

    @Override public void onGoogleLogin(@NonNull Activity context) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(getGoogleApiClient(context));
        context.startActivityForResult(signInIntent, GOOGLE_LOGIN_REQUEST_CODE);
    }

    @Override public void onFacebookLogin(@NonNull LoginButton loginButton) {

    }

    @NonNull @Override public FirebaseAuth getFirebaseAuth() {
        if (mAuth == null) mAuth = FirebaseAuth.getInstance();
        return mAuth;
    }

    @NonNull @Override public GoogleSignInOptions googleSignInOptions(@NonNull Context context) {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
    }

    @NonNull @Override public GoogleApiClient getGoogleApiClient(@NonNull Activity context) {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(context)
                    .enableAutoManage((FragmentActivity) context, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions(context.getApplicationContext()))
                    .build();
        }
        return googleApiClient;
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GOOGLE_LOGIN_REQUEST_CODE) {
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result != null && result.isSuccess()) {
                    getView().onGoogleLoginSuccessfully(result);
                } else {
                    getView().showMessage(R.string.login_fail);
                }
            }
        }
    }

    @Override public void handleGoogleLogin(@NonNull Activity context, @NonNull GoogleSignInResult result) {
        getView().showProgress();
        GoogleSignInAccount acct = result.getSignInAccount();
        if (acct != null && !InputHelper.isEmpty(acct.getIdToken())) {
            Logger.e(acct, acct.getIdToken(), acct.getEmail());
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(credential).addOnCompleteListener(context, this);
        } else {
            Logger.e("token is null");
            getView().hideProgress();
            getView().showMessage(R.string.login_fail);
        }
        if (acct != null) Logger.e(acct.getEmail());
    }

    @Override public void onStart() {
        getFirebaseAuth().addAuthStateListener(this);
    }

    @Override public void onStop() {
        getFirebaseAuth().removeAuthStateListener(this);
    }

    @Override public void onFinish(@NonNull Activity activity) {
        PrefHelper.set(PrefConstance.SKIPPED_LOGIN, true);
        activity.startActivity(new Intent(activity, MainView.class));
        activity.finish();
    }

    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        getView().hideProgress();
        getView().showMessage(connectionResult.getErrorMessage());
    }

    @Override public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        getView().hideProgress();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            getView().onSuccessfullyLoggedIn();
        }
    }

    @Override public void onComplete(@NonNull Task<AuthResult> task) {
        getView().hideProgress();
        if (task.isSuccessful()) {
            getView().onSuccessfullyLoggedIn();
        } else {
            getView().showMessage(R.string.login_fail);
        }
    }
}
