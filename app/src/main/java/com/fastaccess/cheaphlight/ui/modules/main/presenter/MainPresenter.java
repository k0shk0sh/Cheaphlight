package com.fastaccess.cheaphlight.ui.modules.main.presenter;

import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.main.model.MainMvp;

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
        return false;
    }

    @Override public boolean isDrawerOpen(@NonNull DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            getView().closeOpenDrawer(true);
            return true;
        }
        return false;
    }
}
