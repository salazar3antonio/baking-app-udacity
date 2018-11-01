package com.studentproject.bakingappudacity.data;

import java.util.List;

public class Step {

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
}
