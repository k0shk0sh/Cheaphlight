package com.fastaccess.cheaphlight;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.helper.TypeFaceHelper;
import com.fastaccess.cheaphlight.ui.modules.main.view.MainView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

import static com.fastaccess.cheaphlight.BuildConfig.DEBUG;

/**
 * Created by Kosh on 24 May 2016, 7:51 PM
 */

public class App extends Application {

    private static App instance;

    @Override public void onCreate() {
        super.onCreate();
        instance = this;
        PrefHelper.init(this);
        TypeFaceHelper.generateTypeface(this);
        CustomActivityOnCrash.setRestartActivityClass(MainView.class);
        CustomActivityOnCrash.setShowErrorDetails(DEBUG);
        CustomActivityOnCrash.install(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public static App getInstance() {
        return instance;
    }

    public static Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .create();
    }
}
