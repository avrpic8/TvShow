package com.smartelectronics.tvshow.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.responses.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

public class MostPopularTvShowViewModel extends AndroidViewModel {

    private Repository repository;
    public List<TvShow> tvShowItemsList;

    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private boolean allowRefreshList = true;

    public MostPopularTvShowViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
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
