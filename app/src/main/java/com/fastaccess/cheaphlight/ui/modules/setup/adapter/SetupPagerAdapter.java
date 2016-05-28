package com.fastaccess.cheaphlight.ui.modules.setup.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.fastaccess.cheaphlight.ui.modules.setup.view.MyCountryView;
import com.fastaccess.cheaphlight.ui.modules.setup.view.MyFavCountryView;

/**
 * Created by Kosh on 27 May 2016, 8:57 PM
 */

public class SetupPagerAdapter extends FragmentStatePagerAdapter {

    public SetupPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = MyCountryView.getInstance();
        } else if (position == 1) {
            fragment = MyFavCountryView.getInstance();
        }
        return fragment;
    }

    @Override public int getCount() {
        return 2;
    }
}
