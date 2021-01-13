package com.example.apidemo.data;

import com.example.apidemo.models.ImageInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RequestApi {
    //https://pixabay.com/api?key=19836406-6a771e46b7f83a217014461ff&image_type=photo&page=3
    @GET("?")
    Call<ImageInfo> getAll(@Query("key") String key,
                           @Query("image_type") String imageType,
                           @Query("q") String q,
                           @Query("page") int page
    );
}
