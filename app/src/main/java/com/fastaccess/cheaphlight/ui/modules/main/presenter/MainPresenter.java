package com.fastaccess.cheaphlight.ui.modules.main.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.PrefConstance;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.main.model.MainMvp;
import com.fastaccess.cheaphlight.ui.modules.security.view.LoginView;
import com.fastaccess.cheaphlight.ui.modules.suggestions.view.SuggestionsView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Kosh on 25 May 2016, 9:20 PM
 */

public class MainPresenter extends BasePresenter<MainMvp.View> implements MainMvp.Presenter {

    private MainPresenter(MainMvp.View view) {
        super(view);
    }

    public static MainPresenter with(MainMvp.View view) {
        return new MainPresenter(view);
    }

    @Override public boolean onNavigationItemSelected(MenuItem item) {
        getView().closeOpenDrawer(true);
        switch (item.getItemId()) {
            case R.id.logout:
                getView().logout();
                return true;
            case R.id.settings:
                getView().onNavigateTo(MainMvp.SETTINGS);
                return true;
            case R.id.suggestions:
                getView().onNavigateTo(MainMvp.SUGGESTIONS);
                return true;
        }
        return false;
    }

    @Override public boolean isDrawerOpen(@NonNull DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getView().closeOpenDrawer(true);
            return true;
        }
        return false;
    }

    @Override public void navigateTo(@NonNull FragmentManager manager, @MainMvp.NavigationMode int mode) {
        switch (mode) {
            case MainMvp.SETTINGS:
                break;
            case MainMvp.SUGGESTIONS:
                manager.beginTransaction()
                        .replace(R.id.container, SuggestionsView.getInstance(), String.valueOf(mode))
                        .commit();
                break;
        }
    }

    @Override public void logout(@NonNull final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.logout)
                .setMessage(R.string.confirm_message)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.logout, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        FirebaseAuth.getInstance().signOut();
                        PrefHelper.set(PrefConstance.SKIPPED_LOGIN, false);
                        context.startActivity(new Intent(context, LoginView.class));
                        ((Activity) context).finish();
                    }
                })
                .show();
    }

    @Override public void displayUserDetails(@NonNull NavigationView navigationView) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            View header = navigationView.getHeaderView(0);
            getView().setupUserDetails(user, header);
        }
    }

    @Override public void openDrawer() {
        getView().closeOpenDrawer(false);
    }
}
