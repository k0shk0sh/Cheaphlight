package com.fastaccess.cheaphlight.ui.modules.main.view;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.ui.base.BaseActivity;

public class MainView extends BaseActivity {

    @Override protected int layout() {
        return R.layout.activity_main;
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

}
