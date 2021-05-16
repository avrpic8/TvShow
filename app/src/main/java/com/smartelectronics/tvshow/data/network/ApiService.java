package com.smartelectronics.tvshow.data.network;

import com.smartelectronics.tvshow.models.TvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<TvShow> getMostPopularTvShows(@Query("page") int page);
}
