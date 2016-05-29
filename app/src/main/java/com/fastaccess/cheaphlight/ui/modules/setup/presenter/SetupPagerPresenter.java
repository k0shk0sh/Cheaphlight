package com.fastaccess.cheaphlight.ui.modules.setup.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import com.fastaccess.cheaphlight.ui.base.mvp.presenter.BasePresenter;
import com.fastaccess.cheaphlight.ui.modules.main.view.MainView;
import com.fastaccess.cheaphlight.ui.modules.setup.adapter.SetupPagerAdapter;
import com.fastaccess.cheaphlight.ui.modules.setup.model.SetupPagerMvp;
import com.fastaccess.cheaphlight.ui.modules.setup.transformer.SetupTransformer;
import com.fastaccess.cheaphlight.ui.modules.setup.view.SetupPagerView;

/**
 * Created by Kosh on 29 May 2016, 12:47 AM
 */

public class SetupPagerPresenter extends BasePresenter<SetupPagerMvp.View> implements SetupPagerMvp.Presenter {

    protected SetupPagerPresenter(SetupPagerMvp.View view) {
        super(view);
    }

    public static SetupPagerPresenter with(SetupPagerMvp.View view) {
        return new SetupPagerPresenter(view);
    }

    @Override public void initializePager(@NonNull SetupPagerView pagerView, @NonNull ViewPager pager) {
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(new SetupPagerAdapter(pagerView.getSupportFragmentManager()));
        pager.setPageTransformer(true, new SetupTransformer());
    }

    @Override public void onNext(@NonNull ViewPager pager) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }

    @Override public void onFinish(@NonNull Activity context) {
        context.startActivity(new Intent(context, MainView.class));
        context.finish();
    }
}
