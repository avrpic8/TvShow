package com.smartelectronics.tvshow.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.smartelectronics.tvshow.data.Repository;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.responses.TvShowDetailsResponse;

import io.reactivex.Completable;

public class TvShowDetailsViewModel extends AndroidViewModel {

    private Repository repository;

    public MutableLiveData<String> toast = new MutableLiveData<String>();
    public LiveData<TvShowDetailsResponse> tvShowDetailsResponse = new MutableLiveData<>();


    public TvShowDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void getTvShowDetails(int showId){
        tvShowDetailsResponse = repository.remote.getTvShowDetails(showId);
    }

    public Completable addToWatchList(TvShow tvShow){
        return repository.local.addWatchList(tvShow);
    }

}
