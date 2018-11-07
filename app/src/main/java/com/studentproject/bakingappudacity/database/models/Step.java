package com.studentproject.bakingappudacity.database.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    private int id;
    private String shortDescription;
    private String description;
    private String videoUrl;
    private String thumbnailVideoUrl;

    public Step(int id, String shortDescription, String description, String videoUrl, String thumbnailVideoUrl) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailVideoUrl = thumbnailVideoUrl;
    }

    public Step(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoUrl = in.readString();
        thumbnailVideoUrl = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailVideoUrl() {
        return thumbnailVideoUrl;
    }

    public void setThumbnailVideoUrl(String thumbnailVideoUrl) {
        this.thumbnailVideoUrl = thumbnailVideoUrl;
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoUrl);
        dest.writeString(thumbnailVideoUrl);
    }
}
