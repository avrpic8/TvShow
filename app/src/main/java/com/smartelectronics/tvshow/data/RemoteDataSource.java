package com.smartelectronics.tvshow.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smartelectronics.tvshow.data.network.ApiClient;
import com.smartelectronics.tvshow.data.network.ApiService;
import com.smartelectronics.tvshow.responses.TvShowDetailsResponse;
import com.smartelectronics.tvshow.responses.TvShowResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

    private ApiService apiService;

    public RemoteDataSource(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TvShowResponse> getMostPopularTvShows(int page){
        MutableLiveData<TvShowResponse> data = new MutableLiveData<>();
        apiService.getMostPopularTvShows(page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(int showId){
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
