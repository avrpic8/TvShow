package com.smartelectronics.tvshow.data;

import android.content.Context;

import com.smartelectronics.tvshow.data.database.TvShowDao;
import com.smartelectronics.tvshow.data.database.TvShowDataBase;
import com.smartelectronics.tvshow.models.TvShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class LocalDataSource {

    private TvShowDao tvShowDao;

    public LocalDataSource(Context context) {
        tvShowDao = TvShowDataBase.getInstance(context).tvShowDao();
    }

    public Flowable<TvShow> getTvShowById(String tvShowId){
        return tvShowDao.getTvShowById(tvShowId);
    }

    public Flowable<List<TvShow>> getWatchList(){
        return tvShowDao.getWatchList();
     }

    public Completable addWatchList(TvShow tvShow){
        return tvShowDao.addWatchList(tvShow);
    }

    public Completable removeFromWatchList(TvShow tvShow) {
        return tvShowDao.removeFromWatchList(tvShow);
    }
}
