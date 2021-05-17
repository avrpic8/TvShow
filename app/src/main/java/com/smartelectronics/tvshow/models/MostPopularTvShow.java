package com.smartelectronics.tvshow.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MostPopularTvShow {

	@SerializedName("total")
	private String total;

	@SerializedName("page")
	private int page;

	@SerializedName("pages")
	private int pages;

	@SerializedName("tv_shows")
	private List<MostPopularTvShowItems> tvShows;

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

	public List<MostPopularTvShowItems> getTvShows() {
		return tvShows;
	}

	public void setTvShows(List<MostPopularTvShowItems> tvShows) {
		this.tvShows = tvShows;
	}
}