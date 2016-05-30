package com.fastaccess.cheaphlight.ui.modules.main.model;

import android.app.Activity;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseUser;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Kosh on 25 May 2016, 9:17 PM
 */

public interface MainMvp {
    int SUGGESTIONS = 0;
    int SETTINGS = 2;

    @IntDef({SUGGESTIONS, SETTINGS})
    @Retention(RetentionPolicy.SOURCE) @interface NavigationMode {}


    interface View {
        void closeOpenDrawer(boolean close);

        void setupUserDetails(@NonNull FirebaseUser user, @NonNull android.view.View header);

        void onNavigateTo(@NavigationMode int mode);

        void logout();
    }

    interface Presenter extends NavigationView.OnNavigationItemSelectedListener {
        void displayUserDetails(@NonNull NavigationView navigationView);

        void openDrawer();

        boolean isDrawerOpen(@NonNull DrawerLayout drawerLayout);

        void navigateTo(@NonNull FragmentManager manager, @NavigationMode int mode);

        void logout(@NonNull Activity context);
    }
}
