package com.fastaccess.cheaphlight.ui.modules.setup.view;

import android.os.Bundle;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.ui.base.BaseActivity;
import com.fastaccess.cheaphlight.ui.modules.setup.model.SetupPagerMvp;
import com.fastaccess.cheaphlight.ui.modules.setup.presenter.SetupPagerPresenter;
import com.fastaccess.cheaphlight.ui.widgets.ViewPagerView;

import butterknife.BindView;

/**
 * Created by Kosh on 29 May 2016, 12:40 AM
 */

public class SetupPagerView extends BaseActivity implements SetupPagerMvp.View {

    @BindView(R.id.pager) ViewPagerView pager;

    private SetupPagerPresenter presenter;

    @Override protected int layout() {
        return R.layout.setup_layout;
    }

    @Override protected boolean isTransparent() {
        return false;
    }

    @Override protected boolean canBack() {
        return false;
    }

    @Override protected boolean isSecured() {
        return false;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().initializePager(this, pager);
    }

    @Override public void onNext() {
        getPresenter().onNext(pager);
    }

    @Override public void onFinishActivity() {
        getPresenter().onFinish(this);
    }

    public SetupPagerPresenter getPresenter() {
        if (presenter == null) presenter = SetupPagerPresenter.with(this);
        return presenter;
    }
}
