package com.fastaccess.cheaphlight.data.network;

import android.support.annotation.NonNull;

import com.fastaccess.cheaphlight.data.model.FlightsResponseModel;
import com.fastaccess.cheaphlight.data.model.RequestModel;
import com.fastaccess.cheaphlight.data.network.service.FlightsService;

import java.util.List;

import rx.Observable;

/**
 * Created by Kosh on 31 May 2016, 12:07 AM
 */

public class NetworkManager {

    private final FlightsService mBourbonService;

    private NetworkManager(FlightsService flightsService) {
        mBourbonService = flightsService;
    }

    public static NetworkManager with(FlightsService flightsService) {
        return new NetworkManager(flightsService);
    }

    public Observable<List<FlightsResponseModel>> getShots(@NonNull RequestModel body) {
        return mBourbonService.getSuggestions(body);
    }

}
