package com.smartelectronics.tvshow.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.responses.TvShowDetailsResponse;

public class TvShowDetailsViewModel extends ViewModel {

    private Repository repository;
    private boolean allowRefreshList = true;

    public LiveData<TvShowDetailsResponse> tvShowDetailsResponse = new MutableLiveData<>();


    public TvShowDetailsViewModel() {
        repository = new Repository();
    }

    public void getTvShowDetails(int showId){
        tvShowDetailsResponse = repository.remote.getTvShowDetails(showId);
    }

    public boolean isAllowRefreshList() {
        return allowRefreshList;
    }

    public void setAllowRefreshList(boolean allowRefreshList) {
        this.allowRefreshList = allowRefreshList;
    }
}
