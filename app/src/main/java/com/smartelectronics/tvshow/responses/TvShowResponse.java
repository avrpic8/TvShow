package com.smartelectronics.tvshow.responses;

import com.google.gson.annotations.SerializedName;
import com.smartelectronics.tvshow.models.TvShow;

import java.util.List;

public class TvShowResponse {

    @SerializedName("total")
    private String total;

    @SerializedName("page")
    private int page;

    @SerializedName("pages")
    private int pages;

    @SerializedName("tv_shows")
    private List<TvShow> tvShows;

    public String getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public List<TvShow> getTvShows() {
        return tvShows;
    }
}

