package com.fastaccess.cheaphlight.ui.modules.main.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.ui.base.BaseActivity;
import com.fastaccess.cheaphlight.ui.modules.main.model.MainMvp;
import com.fastaccess.cheaphlight.ui.modules.main.presenter.MainPresenter;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import icepick.State;

public class MainView extends BaseActivity implements MainMvp.View {

    @BindView(R.id.navigation) NavigationView navigation;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
    @State @MainMvp.NavigationMode int mode;

    private MainPresenter presenter;

    @Override protected int layout() {
        return R.layout.activity_main;
    }

    @Override protected boolean isTransparent() {
        return true;
    }

    @Override protected boolean canBack() {
        return false;
    }

    @Override protected boolean isSecured() {
        return false;
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getPresenter().navigateTo(getSupportFragmentManager(), MainMvp.SUGGESTIONS);
        }
        setToolbarIcon(R.drawable.ic_menu);
        navigation.setNavigationItemSelectedListener(getPresenter());
        getPresenter().displayUserDetails(navigation);
    }

    @Override public void closeOpenDrawer(boolean close) {
        if (close) drawerLayout.closeDrawer(GravityCompat.START);
        else drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override public void setupUserDetails(@NonNull FirebaseUser user, @NonNull View header) {
        ((TextView) header.findViewById(R.id.username)).setText(user.getDisplayName());
        ((TextView) header.findViewById(R.id.email)).setText(user.getEmail());
        if (!InputHelper.isEmpty(user.getPhotoUrl())) {
            App.getInstance().getImageLoader()
                    .displayImage("" + user.getPhotoUrl(), ((CircleImageView) header.findViewById(R.id.image_avatar)),
                            App.getInstance().getOptions());
        }
    }

    @Override public void onNavigateTo(@MainMvp.NavigationMode int mode) {
        this.mode = mode;
        getPresenter().navigateTo(getSupportFragmentManager(), mode);
    }

    @Override public void logout() {
        getPresenter().logout(this);
    }

    @Override public void onBackPressed() {
        Logger.e(getPresenter().isDrawerOpen(drawerLayout));
        if (!getPresenter().isDrawerOpen(drawerLayout)) {
            super.onBackPressed();
        }
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getPresenter().openDrawer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public MainPresenter getPresenter() {
        if (presenter == null) presenter = MainPresenter.with(this);
        return presenter;
    }
}
