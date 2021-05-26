package com.smartelectronics.tvshow.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.responses.TvShowResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private Repository repository;

    public List<TvShow> tvShowItemsList;
    private int currentPage = 1;
    private int totalAvailablePages = 1;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        tvShowItemsList = new ArrayList<>();
    }

    public LiveData<TvShowResponse> searchTvShow(String query, int page){
        return repository.remote.searchTvShow(query, page);
    }

    public int getCurrentPage(){
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalAvailablePages(int totalAvailablePages) {
        this.totalAvailablePages = totalAvailablePages;
    }

    public void increaseCurrentPage(){
        if(currentPage <= totalAvailablePages)
            currentPage += 1;
    }
}
