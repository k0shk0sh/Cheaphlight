package com.fastaccess.cheaphlight.ui.modules.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.ui.base.BaseActivity;
import com.fastaccess.cheaphlight.ui.modules.main.model.MainMvp;
import com.fastaccess.cheaphlight.ui.modules.main.presenter.MainPresenter;

import butterknife.BindView;

public class MainView extends BaseActivity implements MainMvp.View {

    @BindView(R.id.navigation) NavigationView navigation;
    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
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
        navigation.setNavigationItemSelectedListener(getPresenter());
    }

    @Override public void closeOpenDrawer(boolean close) {
        if (close) drawerLayout.closeDrawer(GravityCompat.START);
        else drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override public void onBackPressed() {
        Logger.e(getPresenter().isDrawerOpen(drawerLayout));
        if (!getPresenter().isDrawerOpen(drawerLayout)) {
            super.onBackPressed();
        }
    }

    public MainPresenter getPresenter() {
        if (presenter == null) presenter = MainPresenter.with(this);
        return presenter;
    }
}
