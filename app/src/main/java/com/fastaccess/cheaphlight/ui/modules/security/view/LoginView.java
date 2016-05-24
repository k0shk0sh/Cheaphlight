package com.fastaccess.cheaphlight.ui.modules.security.view;

import android.content.Intent;
import android.os.Bundle;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.ui.base.BaseActivity;

/**
 * Created by Kosh on 24 May 2016, 9:08 PM
 */

public class LoginView extends BaseActivity {

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

//    @OnClick(R.id.login) void onClick() {
//        startActivityForResult(
//                AuthUI.getInstance().createSignInIntentBuilder()
//                        .setTheme(R.style.LoginTheme)
//                        .setProviders(AuthUI.FACEBOOK_PROVIDER, AuthUI.GOOGLE_PROVIDER)
//                        .build(), RC_SIGN_IN);
//    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.e(requestCode, resultCode, data);
    }
}
