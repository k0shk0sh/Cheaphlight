package com.fastaccess.cheaphlight.ui.modules.setup.view;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.AnimHelper;
import com.fastaccess.cheaphlight.helper.Logger;
import com.fastaccess.cheaphlight.ui.base.BaseFragment;
import com.fastaccess.cheaphlight.ui.modules.setup.adapter.MyCountriesAdapter;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseViewHolder;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.ResizeableRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;

/**
 * Created by Kosh on 27 May 2016, 7:54 PM
 */

public class MyFavCountryView extends BaseFragment {
    @BindView(R.id.next) ImageView next;
    @State ArrayList<String> flags = new ArrayList<>();
    @BindView(R.id.recycler) ResizeableRecyclerView recycler;
    private MyCountriesAdapter adapter;

    @Override protected int fragmentLayout() {
        return R.layout.my_fav_country_page;
    }

    @Override protected boolean isRetainRequired() {
        return false;
    }

    @Override protected void onFragmentCreated(final View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            try {
                flags.addAll(getAllFlags());
            } catch (IOException e) {
                e.printStackTrace();
                showMessage(R.string.error_loading_flags);
            }
        }
        Logger.e(flags);
        adapter = new MyCountriesAdapter(flags, new BaseViewHolder.OnItemClickListener<String>() {
            @Override public void onItemClick(int position, View v, String item) {}

            @Override public void onItemLongClick(int position, View v, String item) {
                View view = v.findViewById(R.id.selected);
                view.setSelected(!view.isShown());
                AnimHelper.animateVisibityWithTranslate(!view.isShown(), view);
            }
        });
        recycler.setAdapter(adapter);
    }

    @OnClick(R.id.next) public void onClick() {

    }

    @NonNull private List<String> getAllFlags() throws IOException {
        List<String> paths = new ArrayList<>();
        AssetManager assetManager = getContext().getAssets();
        paths.addAll(Arrays.asList(assetManager.list("countries")));
        return paths;
    }

    public static Fragment getInstance() {
        return new MyFavCountryView();
    }

}
