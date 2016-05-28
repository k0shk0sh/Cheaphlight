package com.fastaccess.cheaphlight.ui.modules.setup.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.fastaccess.cheaphlight.R;
import com.fastaccess.cheaphlight.ui.base.BaseFragment;
import com.fastaccess.cheaphlight.ui.modules.setup.adapter.MyCountriesAdapter;
import com.fastaccess.cheaphlight.ui.widgets.FontAutoCompleteEditText;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.BaseViewHolder;
import com.fastaccess.cheaphlight.ui.widgets.recyclerview.ResizeableRecyclerView;

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
    @BindView(R.id.recycler) ResizeableRecyclerView recycler;
    @BindView(R.id.country) FontAutoCompleteEditText country;
    @State ArrayList<String> myFavList = new ArrayList<>();
    private MyCountriesAdapter adapter;
    private List<String> countries;

    @Override protected int fragmentLayout() {
        return R.layout.my_fav_country_page;
    }

    @Override protected boolean isRetainRequired() {
        return false;
    }

    @Override protected void onFragmentCreated(final View view, @Nullable Bundle savedInstanceState) {
        countries = Arrays.asList(getResources().getStringArray(R.array.countries_list_human));
        country.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, countries));
        adapter = new MyCountriesAdapter(myFavList, new BaseViewHolder.OnItemClickListener<String>() {
            @Override public void onItemClick(int position, View v, String item) {
                adapter.removeItem(position);
            }

            @Override public void onItemLongClick(int position, View v, String item) {
                adapter.removeItem(position);
            }
        });
        recycler.setAdapter(adapter);
        country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (adapter.getItemCount() < 5) {
                    if (!myFavList.contains(parent.getItemAtPosition(position).toString())) {
                        adapter.addItem(parent.getItemAtPosition(position).toString());
                    }
                    country.setText("");
                } else {
                    showMessage(R.string.favorite_maximum_error);
                }
            }
        });
    }

    @OnClick(R.id.next) public void onClick() {

    }

    public static Fragment getInstance() {
        return new MyFavCountryView();
    }
}
