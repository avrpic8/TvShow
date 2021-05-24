package com.smartelectronics.tvshow.data;

import android.content.Context;

public class Repository {

    public RemoteDataSource remote;
    public LocalDataSource local;

    public Repository(Context context) {
        local  = new LocalDataSource(context);
        remote = new RemoteDataSource();
    }
}
