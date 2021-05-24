package com.smartelectronics.tvshow.data;

import android.content.Context;

import com.smartelectronics.tvshow.data.database.TvShowDao;
import com.smartelectronics.tvshow.data.database.TvShowDataBase;
import com.smartelectronics.tvshow.models.TvShow;

import io.reactivex.Completable;

public class LocalDataSource {

    private TvShowDao tvShowDao;

    public LocalDataSource(Context context) {
        tvShowDao = TvShowDataBase.getInstance(context).tvShowDao();
    }

    public Completable addWatchList(TvShow tvShow){
        return tvShowDao.addWatchList(tvShow);
    }
}
