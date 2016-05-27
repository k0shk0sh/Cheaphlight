package com.fastaccess.cheaphlight.ui.modules.setup.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fastaccess.cheaphlight.App;
import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.ColorHelper;
import com.fastaccess.cheaphlight.ui.modules.setup.view.CountriesHolder;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseRecyclerAdapter;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseViewHolder;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Kosh on 27 May 2016, 9:00 PM
 */

public class MyCountriesAdapter extends BaseRecyclerAdapter<String, CountriesHolder, BaseViewHolder.OnItemClickListener<String>> {

    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public MyCountriesAdapter(@NonNull List<String> data, @Nullable BaseViewHolder.OnItemClickListener<String> listener) {
        super(data, listener);
        this.imageLoader = App.getInstance().getImageLoader();
        this.options = App.getInstance().getInMemoryOnly();
    }

    @Override protected CountriesHolder viewHolder(ViewGroup parent, int viewType) {
        return new CountriesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.county_row_item, parent, false), this);
    }

    @Override public void onBindViewHolder(CountriesHolder holder, int position) {
        imageLoader.displayImage("assets://countries/" + getItem(position), holder.countryFlag, options);
        holder.cardView.setCardBackgroundColor(ColorHelper.MATERIAL.getColor(position));
    }
}
