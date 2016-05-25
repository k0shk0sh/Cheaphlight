package com.fastaccess.cheaphlight.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.fastaccess.cheaphlight.BuildConfig;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.AppHelper;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.helper.ViewHelper;
import com.fastaccess.cheaphlight.ui.modules.security.view.LoginView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import icepick.Icepick;

import static com.fastaccess.cheaphlight.helper.PrefConstance.SKIPPED_LOGIN;

/**
 * Created by Kosh on 24 May 2016, 8:48 PM
 */

public abstract class BaseActivity extends AppCompatActivity {

//    static {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
//    }

    @LayoutRes protected abstract int layout();

    protected abstract boolean isTransparent();

    protected abstract boolean canBack();

    protected abstract boolean isSecured();

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (layout() != 0) {
            setContentView(layout());
            ButterKnife.bind(this);
        }
        Icepick.setDebug(BuildConfig.DEBUG);
        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            Icepick.restoreInstanceState(this, savedInstanceState);
        }
        setupToolbarAndStatusBar();
        if (!PrefHelper.getBoolean(SKIPPED_LOGIN)) {
            if (!isUserLoggedIn() && !isSecured()) {
                startActivity(new Intent(this, LoginView.class));
                finish();
            }
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (canBack()) {
            if (item.getItemId() == android.R.id.home) {
                supportFinishAfterTransition();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbarAndStatusBar() {
        if (AppHelper.isLollipopOrHigher()) {
            changeAppColor();
        }
        if (findViewById(R.id.toolbar) != null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (canBack()) {
                if (getSupportActionBar() != null) {
                    if (toolbar != null) {
                        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                supportFinishAfterTransition();
                            }
                        });
                    }
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
    }

    protected void setToolbarIcon(@DrawableRes int res) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(res);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void changeAppColor() {
        if (AppHelper.isLollipopOrHigher()) {
            if (!isTransparent()) {
                getWindow().setStatusBarColor(ViewHelper.getPrimaryDarkColor(this));
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            }
        }
    }

    private boolean isUserLoggedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }
}
