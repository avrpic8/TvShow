package com.smartelectronics.tvshow.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.TvShow;

public class MostPopularTvShowViewModel extends ViewModel {

    private Repository repository;

    public MostPopularTvShowViewModel() {
        repository = new Repository();
    }

    public LiveData<TvShow> getMostPopularTvShows(int page){
        return repository.remote.getMostPopularTvShows(page);
    }
}
