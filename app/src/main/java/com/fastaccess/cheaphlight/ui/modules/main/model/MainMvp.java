package com.fastaccess.cheaphlight.ui.modules.main.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Kosh on 25 May 2016, 9:17 PM
 */

public interface MainMvp {

    interface View {
        void closeOpenDrawer(boolean close);

        void setupUserDetails(@NonNull FirebaseUser user, @NonNull android.view.View header);

        void logout();
    }

    interface Presenter extends NavigationView.OnNavigationItemSelectedListener {
        void displayUserDetails(@NonNull NavigationView navigationView);

        void openDrawer();

        boolean isDrawerOpen(@NonNull DrawerLayout drawerLayout);

        void logout(@NonNull Context context);
    }
}
