package com.smartelectronics.tvshow.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.TvShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WatchListViewModel extends AndroidViewModel {

    private Repository repository;

    public MutableLiveData<Boolean> dataReady = new MutableLiveData<>();
    public MutableLiveData<Boolean> dataRemove = new MutableLiveData<>();
    public MutableLiveData<String> toast = new MutableLiveData<String>();
    private Boolean showToast = false;

    public WatchListViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public Boolean getShowToast() {
        return showToast;
    }

    public void setShowToast(Boolean showToast) {
        this.showToast = showToast;
    }

    public Flowable<List<TvShow>> getWatchList(){
        return repository.local.getWatchList();
    }

    public Completable removeFromWatchList(TvShow tvShow){
        return repository.local.removeFromWatchList(tvShow);
    }
}
