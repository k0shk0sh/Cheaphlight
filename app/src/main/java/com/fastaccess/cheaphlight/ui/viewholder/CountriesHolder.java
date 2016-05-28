package com.fastaccess.cheaphlight.ui.viewholder;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseRecyclerAdapter;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Kosh on 27 May 2016, 9:01 PM
 */

public class CountriesHolder extends BaseViewHolder {
    @BindView(R.id.card) public CardView cardView;
    @BindView(R.id.countryName) public TextView countryName;

    public CountriesHolder(View itemView, BaseRecyclerAdapter adapter) {
        super(itemView, adapter);
    }
}
