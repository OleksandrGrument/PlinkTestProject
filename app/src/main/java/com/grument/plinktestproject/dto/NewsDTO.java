package com.grument.plinktestproject.dto;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class NewsDTO {

    public NewsDTO() {}

    @SerializedName("title")
    private String title;

    @SerializedName("url")
    private String url;

    @SerializedName("multimedia")
    private ArrayList<MultimediaDTO> multimediaDTOs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList<MultimediaDTO> getMultimediaDTOs() {
        return multimediaDTOs;
    }

    public void setMultimediaDTOs(ArrayList<MultimediaDTO> multimediaDTOs) {
        this.multimediaDTOs = multimediaDTOs;
    }

    @Override
    public String toString() {
        return "NewsDTO{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", multimediaDTOs=" + multimediaDTOs +
                '}';
    }
}
