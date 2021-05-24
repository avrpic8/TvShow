package com.smartelectronics.tvshow.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.smartelectronics.tvshow.models.TvShow;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


@Dao
public interface TvShowDao {

    @Query("SELECT * FROM tvShows")
    Flowable<List<TvShow>> getWatchList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addWatchList(TvShow tvShow);

    @Delete
    void removeFromWatchList(TvShow tvShow);

}
