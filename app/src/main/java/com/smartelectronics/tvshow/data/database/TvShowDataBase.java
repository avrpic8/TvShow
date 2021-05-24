package com.smartelectronics.tvshow.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.smartelectronics.tvshow.models.TvShow;

@Database(entities = TvShow.class, version = 1, exportSchema = false)
public abstract class TvShowDataBase extends RoomDatabase {

    private static TvShowDataBase tvShowDataBase;

    public static synchronized TvShowDataBase getInstance(Context context){
        if(tvShowDataBase == null){
            tvShowDataBase = Room.databaseBuilder(
                    context,
                    TvShowDataBase.class,
                    "tv_show_db"
            ).build();
        }

        return tvShowDataBase;
    }

    public abstract TvShowDao tvShowDao();
}
