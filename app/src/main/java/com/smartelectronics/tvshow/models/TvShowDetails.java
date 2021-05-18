package com.smartelectronics.tvshow.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class TvShowDetails {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("url")
    private String url;

    @SerializedName("description")
    private String description;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("end_date")
    @Nullable
    private String endDate;

    @SerializedName("country")
    private String country;

    @SerializedName("status")
    private String status;

    @SerializedName("runtime")
    private String runtime;

    @SerializedName("image_path")
    private String imagePath;

    @SerializedName("rating")
    private String rating;

    @SerializedName("rating_count")
    private String ratingCount;

    @SerializedName("genres")
    private String[] genres;

    @SerializedName("pictures")
    private String[] pictures;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    @Nullable
    public String getEndDate() {
        return endDate;
    }

    public String getCountry() {
        return country;
    }

    public String getStatus() {
        return status;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getRating() {
        return rating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getPictures() {
        return pictures;
    }
}
