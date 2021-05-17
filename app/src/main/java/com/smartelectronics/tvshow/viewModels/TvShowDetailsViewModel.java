package com.smartelectronics.tvshow.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.TvShowDetailsResponse;

public class TvShowDetailsViewModel extends ViewModel {

    private Repository repository;

    public TvShowDetailsViewModel() {
        repository = new Repository();
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String showId){
        return repository.remote.getTvShowDetails(showId);
    }
}
