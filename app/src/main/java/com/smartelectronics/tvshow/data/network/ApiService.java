package com.smartelectronics.tvshow.data.network;

import com.smartelectronics.tvshow.models.MostPopularTvShow;
import com.smartelectronics.tvshow.models.TvShowDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<MostPopularTvShow> getMostPopularTvShows(@Query("page") int page);

    @GET("show-details")
    Call<TvShowDetailsResponse> getTvShowDetails(@Query("q") String showId);
}
