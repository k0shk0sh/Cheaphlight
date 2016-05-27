package com.fastaccess.cheaphlight;

import android.app.Application;
import android.graphics.Bitmap;

import com.facebook.FacebookSdk;
import com.fastaccess.cheaphlight.helper.PrefHelper;
import com.fastaccess.cheaphlight.helper.TypeFaceHelper;
import com.fastaccess.cheaphlight.ui.modules.main.view.MainView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.lang.reflect.Modifier;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

import static com.fastaccess.cheaphlight.BuildConfig.DEBUG;

/**
 * Created by Kosh on 24 May 2016, 7:51 PM
 */

public class App extends Application {

    private static App instance;

    private ImageLoader imageLoader;

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

    public ImageLoader getImageLoader() {
        if (imageLoader == null) imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                    .writeDebugLogs()
                    .threadPriority(Thread.MAX_PRIORITY)
                    .denyCacheImageMultipleSizesInMemory()
                    .build();
            ImageLoader.getInstance().init(config);
        }
        return imageLoader;
    }

    public DisplayImageOptions getOptions() {
        return new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.drawable.ic_plane_accent)
                .showImageOnFail(R.drawable.ic_plane_red)
                .build();
    }

    public DisplayImageOptions getInMemoryOnly() {
        return new DisplayImageOptions.Builder()
                .cacheOnDisk(false)
                .cacheInMemory(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageOnLoading(R.drawable.ic_plane_accent)
                .showImageOnFail(R.drawable.ic_plane_red)
                .build();
    }
}
