package com.example.apidemo.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageInfo {

    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("totalHits")
    @Expose
    public Integer totalHits;
    @SerializedName("hits")
    @Expose
    public List<Hit> hits = null;

    public ImageInfo() {

    }

    public ImageInfo(Integer total, Integer totalHits, List<Hit> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }
}