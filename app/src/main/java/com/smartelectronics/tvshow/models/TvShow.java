package com.smartelectronics.tvshow.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TvShow implements Parcelable {

	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("start_date")
	private String startDate;

	@SerializedName("end_date")
	private Object endDate;

	@SerializedName("country")
	private String country;

	@SerializedName("network")
	private String network;

	@SerializedName("status")
	private String status;

	@SerializedName("image_thumbnail_path")
	private String imageThumbnailPath;

	protected TvShow(Parcel in) {
		id = in.readInt();
		name = in.readString();
		permalink = in.readString();
		startDate = in.readString();
		country = in.readString();
		network = in.readString();
		status = in.readString();
		imageThumbnailPath = in.readString();
	}

	public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
		@Override
		public TvShow createFromParcel(Parcel in) {
			return new TvShow(in);
		}

		@Override
		public TvShow[] newArray(int size) {
			return new TvShow[size];
		}
	};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Object getEndDate() {
		return endDate;
	}

	public void setEndDate(Object endDate) {
		this.endDate = endDate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageThumbnailPath() {
		return imageThumbnailPath;
	}

	public void setImageThumbnailPath(String imageThumbnailPath) {
		this.imageThumbnailPath = imageThumbnailPath;
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
		dest.writeString(startDate);
		dest.writeString(country);
		dest.writeString(network);
		dest.writeString(status);
		dest.writeString(imageThumbnailPath);
	}
}