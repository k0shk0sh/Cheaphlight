package com.fastaccess.cheaphlight.ui.modules.main.model;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

/**
 * Created by Kosh on 25 May 2016, 9:17 PM
 */

public interface MainMvp {

    interface View {
        void closeOpenDrawer(boolean close);
    }

    interface Presenter extends NavigationView.OnNavigationItemSelectedListener {

        boolean isDrawerOpen(@NonNull DrawerLayout drawerLayout);
    }
}
