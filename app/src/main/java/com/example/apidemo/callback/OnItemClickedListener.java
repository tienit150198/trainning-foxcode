package com.example.apidemo.callback;

import com.example.apidemo.models.Hit;

public interface OnItemClickedListener {
    default void onItemClicked(Hit item) {}
}
