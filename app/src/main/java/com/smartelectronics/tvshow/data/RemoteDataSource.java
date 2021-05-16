package com.smartelectronics.tvshow.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smartelectronics.tvshow.data.network.ApiClient;
import com.smartelectronics.tvshow.data.network.ApiService;
import com.smartelectronics.tvshow.models.TvShow;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {

    private ApiService apiService;

    public RemoteDataSource(){
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TvShow> getMostPopularTvShows(int page){
        MutableLiveData<TvShow> data = new MutableLiveData<>();
        apiService.getMostPopularTvShows(page).enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(@NonNull Call<TvShow> call, @NonNull Response<TvShow> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TvShow> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
