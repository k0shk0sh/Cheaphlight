package com.fastaccess.cheaphlight.ui.modules.setup.view;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseRecyclerAdapter;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Kosh on 27 May 2016, 9:01 PM
 */

public class CountriesHolder extends BaseViewHolder {
    @BindView(R.id.countryFlag) public ImageView countryFlag;
    @BindView(R.id.selected) public ImageView selected;
    @BindView(R.id.card) public CardView cardView;

    public CountriesHolder(View itemView, BaseRecyclerAdapter adapter) {
        super(itemView, adapter);
    }
}
