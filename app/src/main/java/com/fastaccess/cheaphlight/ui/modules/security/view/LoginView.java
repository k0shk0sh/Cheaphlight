package com.fastaccess.cheaphlight.ui.modules.security.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.facebook.login.widget.LoginButton;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.PrefConstance;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.ui.base.BaseActivity;
import com.fastaccess.cheaphlight.ui.modules.main.view.MainView;
import com.fastaccess.cheaphlight.ui.widgets.FontButton;
import com.google.android.gms.common.SignInButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kosh on 24 May 2016, 9:08 PM
 */

public class LoginView extends BaseActivity {

    @BindView(R.id.googleLogin) FontButton googleLogin;
    @BindView(R.id.facebookLogin) FontButton facebookLogin;
    @BindView(R.id.googleButton) SignInButton googleButton;
    @BindView(R.id.facebookButton) LoginButton facebookButton;
    @BindView(R.id.skip) FontButton skip;

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

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.googleLogin, R.id.facebookLogin, R.id.skip}) void onClick(View view) {
        switch (view.getId()) {
            case R.id.googleLogin:
                break;
            case R.id.facebookLogin:
                break;
            case R.id.skip:
                PrefHelper.set(PrefConstance.SKIPPED_LOGIN, true);
                startActivity(new Intent(this, MainView.class));
                finish();
                break;
        }
    }
}
