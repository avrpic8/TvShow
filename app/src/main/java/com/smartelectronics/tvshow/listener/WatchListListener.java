package com.smartelectronics.tvshow.listener;

import com.smartelectronics.tvshow.models.TvShow;

public interface WatchListListener {

    void removeTvShowFromWatchList(TvShow tvShow, int position);
}
