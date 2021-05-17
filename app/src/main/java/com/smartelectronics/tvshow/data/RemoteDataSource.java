package com.smartelectronics.tvshow.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smartelectronics.tvshow.data.network.ApiClient;
import com.smartelectronics.tvshow.data.network.ApiService;
import com.smartelectronics.tvshow.models.MostPopularTvShow;
import com.smartelectronics.tvshow.models.TvShowDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

    private ApiService apiService;

    public RemoteDataSource(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<MostPopularTvShow> getMostPopularTvShows(int page){
        MutableLiveData<MostPopularTvShow> data = new MutableLiveData<>();
        apiService.getMostPopularTvShows(page).enqueue(new Callback<MostPopularTvShow>() {
            @Override
            public void onResponse(@NonNull Call<MostPopularTvShow> call, @NonNull Response<MostPopularTvShow> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MostPopularTvShow> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String showId){
        MutableLiveData<TvShowDetailsResponse> data = new MutableLiveData<>();
        apiService.getTvShowDetails(showId).enqueue(new Callback<TvShowDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowDetailsResponse> call, @NonNull Response<TvShowDetailsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TvShowDetailsResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
