package com.example.apidemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("pageURL")
    @Expose
    public String pageURL;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("tags")
    @Expose
    public String tags;
    @Expose
    public String webformatURL;
    @SerializedName("webformatWidth")
    @Expose
    public Integer webformatWidth;
    @SerializedName("webformatHeight")
    @Expose
    public Integer webformatHeight;
    @Expose
    public Integer imageSize;
    @SerializedName("views")
    @Expose
    public Integer views;
    @SerializedName("downloads")
    @Expose
    public Integer downloads;
    @SerializedName("favorites")
    @Expose
    public Integer favorites;
    @SerializedName("likes")
    @Expose
    public Integer likes;
    @SerializedName("comments")
    @Expose
    public Integer comments;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("user")
    @Expose
    public String user;
    @SerializedName("userImageURL")
    @Expose
    public String userImageURL;

    public Hit(Integer id, String pageURL, String type, String tags, String webformatURL, Integer webformatWidth, Integer webformatHeight, Integer imageSize, Integer views, Integer downloads, Integer favorites, Integer likes, Integer comments, Integer userId, String user, String userImageURL) {
        this.id = id;
        this.pageURL = pageURL;
        this.type = type;
        this.tags = tags;
        this.webformatURL = webformatURL;
        this.webformatWidth = webformatWidth;
        this.webformatHeight = webformatHeight;
        this.imageSize = imageSize;
        this.views = views;
        this.downloads = downloads;
        this.favorites = favorites;
        this.likes = likes;
        this.comments = comments;
        this.userId = userId;
        this.user = user;
        this.userImageURL = userImageURL;
    }

    public Hit() {
    }
}