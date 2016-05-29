package com.fastaccess.cheaphlight.ui.modules.security.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.facebook.login.widget.LoginButton;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.ui.base.BaseActivity;
import com.fastaccess.cheaphlight.ui.modules.security.model.LoginMvp;
import com.fastaccess.cheaphlight.ui.modules.security.presenter.LoginPresenter;
import com.fastaccess.cheaphlight.ui.widgets.FontButton;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kosh on 24 May 2016, 9:08 PM
 */

public class LoginView extends BaseActivity implements LoginMvp.View {

    @BindView(R.id.googleLogin) FontButton googleLogin;
    @BindView(R.id.facebookLogin) FontButton facebookLogin;
    @BindView(R.id.googleButton) SignInButton googleButton;
    @BindView(R.id.facebookButton) LoginButton facebookButton;
    @BindView(R.id.skip) FontButton skip;
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;
    private LoginPresenter presenter;

    @OnClick({R.id.googleLogin, R.id.facebookLogin, R.id.skip}) void onClick(View view) {
        switch (view.getId()) {
            case R.id.googleLogin:
                getPresenter().onGoogleLogin(this);
                break;
            case R.id.facebookLogin:
                getPresenter().onFacebookLogin(facebookButton);
                break;
            case R.id.skip:
                getPresenter().onFinish(this);
                break;
        }
    }

    @Override protected int layout() {
        return R.layout.login_layout;
    }

    @Override protected boolean isTransparent() {
        return true;
    }

    @Override protected boolean canBack() {
        return false;
    }

    @Override protected boolean isSecured() {
        return true;
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode, resultCode, data);
    }

    @Override public void showProgress() {
        facebookLogin.setEnabled(false);
        googleLogin.setEnabled(false);
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getString(R.string.in_progress));
        }
        if (!progressDialog.isShowing()) progressDialog.show();
    }

    @Override public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
        facebookLogin.setEnabled(true);
        googleLogin.setEnabled(true);
    }

    @Override public void onGoogleLoginSuccessfully(@NonNull GoogleSignInResult result) {
        getPresenter().handleGoogleLogin(this, result);
    }

    @Override public void showMessage(String errorMessage) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(this)
                    .setPositiveButton(R.string.close, null)
                    .setTitle(R.string.app_name)
                    .create();
        }
        if (alertDialog.isShowing()) alertDialog.dismiss();
        alertDialog.setMessage(errorMessage);
        alertDialog.show();
    }

    @Override public void showMessage(@StringRes int msgId) {
        showMessage(getString(msgId));
    }

    @Override public void onSuccessfullyLoggedIn() {
        getPresenter().onFinish(this);
    }

    @Override protected void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override protected void onStop() {
        getPresenter().onDestroy();
        super.onStop();
    }

    public LoginPresenter getPresenter() {
        if (presenter == null) presenter = LoginPresenter.with(this);
        return presenter;
    }
}
