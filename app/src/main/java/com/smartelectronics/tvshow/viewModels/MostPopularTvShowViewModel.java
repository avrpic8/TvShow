package com.smartelectronics.tvshow.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.responses.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

public class MostPopularTvShowViewModel extends ViewModel {

    private Repository repository;
    public List<TvShow> tvShowItemsList;

    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private boolean allowRefreshList = true;

    public MostPopularTvShowViewModel() {
        repository = new Repository();
        tvShowItemsList = new ArrayList<>();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i("cycle", "onCleared: ");
    }

    public LiveData<TvShowResponse> getMostPopularTvShows(int page){
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

    public boolean isAllowRefreshList() {
        return allowRefreshList;
    }

    public void setAllowRefreshList(boolean allowRefreshList) {
        this.allowRefreshList = allowRefreshList;
    }
}
