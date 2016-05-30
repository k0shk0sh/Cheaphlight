package com.fastaccess.cheaphlight.data.network.service;

import com.fastaccess.cheaphlight.data.model.FlightsResponseModel;
import com.fastaccess.cheaphlight.data.model.RequestModel;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Kosh on 31 May 2016, 12:01 AM
 */
public interface FlightsService {

    @POST() Observable<List<FlightsResponseModel>> getSuggestions(@Body RequestModel model);

}
