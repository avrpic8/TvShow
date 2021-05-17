package com.smartelectronics.tvshow.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TvShow{

	@SerializedName("total")
	private String total;

	@SerializedName("page")
	private int page;

	@SerializedName("pages")
	private int pages;

	@SerializedName("tv_shows")
	private List<TvShowsItem> tvShows;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<TvShowsItem> getTvShows() {
		return tvShows;
	}

	public void setTvShows(List<TvShowsItem> tvShows) {
		this.tvShows = tvShows;
	}
}