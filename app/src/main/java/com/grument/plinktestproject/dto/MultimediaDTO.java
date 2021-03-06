package com.grument.plinktestproject.dto;


import com.google.gson.annotations.SerializedName;

public class MultimediaDTO {

    public MultimediaDTO (){}

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MultimediaDTO{" +
                "url='" + url + '\'' +
                '}';
    }
}
