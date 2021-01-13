package com.example.apidemo.data;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = Constant.BASE_URL;
    private static Retrofit mRetrofit = null;

    public static Retrofit getClient() {
        if (mRetrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true) // reconnect while connect fail
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient) // add client
                    .addConverterFactory(GsonConverterFactory.create()) // auto convert gson
                    .build();
        }

        return mRetrofit;
    }
}
