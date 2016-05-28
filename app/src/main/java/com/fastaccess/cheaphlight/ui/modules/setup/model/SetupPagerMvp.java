package com.fastaccess.cheaphlight.ui.modules.setup.model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.fastaccess.cheaphlight.ui.modules.setup.view.SetupPagerView;

/**
 * Created by Kosh on 29 May 2016, 12:43 AM
 */

public interface SetupPagerMvp {

    interface View {
        void onNext();

        void onFinishActivity();
    }

    interface Presenter {
        void initializePager(@NonNull SetupPagerView pagerView, @NonNull ViewPager pager);

        void onNext(@NonNull ViewPager pager);

        void onFinish(@NonNull Activity context);
    }

}
