package com.smartelectronics.tvshow.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowDetails implements Parcelable {

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

    @SerializedName("episodes")
    private List<Episode> episodes;

    protected TvShowDetails(Parcel in) {
        id = in.readInt();
        name = in.readString();
        permalink = in.readString();
        url = in.readString();
        description = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        country = in.readString();
        status = in.readString();
        runtime = in.readString();
        imagePath = in.readString();
        rating = in.readString();
        ratingCount = in.readString();
        genres = in.createStringArray();
        pictures = in.createStringArray();
    }

    public static final Creator<TvShowDetails> CREATOR = new Creator<TvShowDetails>() {
        @Override
        public TvShowDetails createFromParcel(Parcel in) {
            return new TvShowDetails(in);
        }

        @Override
        public TvShowDetails[] newArray(int size) {
            return new TvShowDetails[size];
        }
    };

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

    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(permalink);
        dest.writeString(url);
        dest.writeString(description);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeString(country);
        dest.writeString(status);
        dest.writeString(runtime);
        dest.writeString(imagePath);
        dest.writeString(rating);
        dest.writeString(ratingCount);
        dest.writeStringArray(genres);
        dest.writeStringArray(pictures);
    }
}
