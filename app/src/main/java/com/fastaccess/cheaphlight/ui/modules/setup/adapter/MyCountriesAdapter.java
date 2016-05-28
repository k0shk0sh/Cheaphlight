package com.fastaccess.cheaphlight.ui.modules.setup.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.helper.InputHelper;
import com.fastaccess.cheaphlight.ui.modules.setup.view.CountriesHolder;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseRecyclerAdapter;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Kosh on 27 May 2016, 9:00 PM
 */

public class MyCountriesAdapter extends BaseRecyclerAdapter<String, CountriesHolder,
        BaseViewHolder.OnItemClickListener<String>> {

    public MyCountriesAdapter(@NonNull List<String> data, @Nullable BaseViewHolder.OnItemClickListener<String> listener) {
        super(data, listener);
    }

    @Override protected CountriesHolder viewHolder(ViewGroup parent, int viewType) {
        return new CountriesHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.county_row_item, parent, false), this);
    }

    @Override public void onBindViewHolder(CountriesHolder holder, int position) {
        if (!InputHelper.isEmpty(getItem(position))) {
            holder.countryName.setText(getItem(position));
        }
    }
}
