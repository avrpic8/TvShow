package com.smartelectronics.tvshow.data.network;

import com.smartelectronics.tvshow.responses.TvShowDetailsResponse;
import com.smartelectronics.tvshow.responses.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<TvShowResponse> getMostPopularTvShows(@Query("page") int page);

    @GET("show-details")
    Call<TvShowDetailsResponse> getTvShowDetails(@Query("q") int showId);

    @GET("search")
    Call<TvShowResponse> searchTvShow(@Query("q") String query, @Query("page") int page);
}
