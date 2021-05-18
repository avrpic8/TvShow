package com.smartelectronics.tvshow.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.responses.TvShowDetailsResponse;

public class TvShowDetailsViewModel extends ViewModel {

    private Repository repository;

    public TvShowDetailsViewModel() {
        repository = new Repository();
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(int showId){
        return repository.remote.getTvShowDetails(showId);
    }
}
