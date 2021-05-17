package com.smartelectronics.tvshow.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.MostPopularTvShow;

public class MostPopularTvShowViewModel extends ViewModel {

    private Repository repository;
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    public MostPopularTvShowViewModel() {
        repository = new Repository();
    }

    public LiveData<MostPopularTvShow> getMostPopularTvShows(int page){
        return repository.remote.getMostPopularTvShows(page);
    }

    public int getCurrentPage(){
        return currentPage;
    }

    public void setTotalAvailablePages(int totalAvailablePages) {
        this.totalAvailablePages = totalAvailablePages;
    }

    public void increaseCurrentPage(){
        if(currentPage <= totalAvailablePages)
            currentPage += 1;
    }
}
