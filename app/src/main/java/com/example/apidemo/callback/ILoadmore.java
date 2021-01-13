package com.example.apidemo.callback;

public interface ILoadmore {
    default void onLoadMore() {}
    default void onStopLoadMore() {}
}
