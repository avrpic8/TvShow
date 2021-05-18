package com.smartelectronics.tvshow.responses;

import com.google.gson.annotations.SerializedName;
import com.smartelectronics.tvshow.models.TvShowDetails;

public class TvShowDetailsResponse {

    @SerializedName("tvShow")
    private TvShowDetails tvShowDetails;

    public TvShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
