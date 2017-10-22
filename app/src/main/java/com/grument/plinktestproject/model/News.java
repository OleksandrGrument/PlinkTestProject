package com.grument.plinktestproject.model;


import io.realm.RealmObject;

public class News extends RealmObject {

    public News(){}

    public News(String title, String newsArticleUrl, String thumbnailUrl) {
        this.title = title;
        this.newsArticleUrl = newsArticleUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    private String title;

    private String newsArticleUrl;

    private String thumbnailUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsArticleUrl() {
        return newsArticleUrl;
    }

    public void setNewsArticleUrl(String newsArticleUrl) {
        this.newsArticleUrl = newsArticleUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", newsArticleUrl='" + newsArticleUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
