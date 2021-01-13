package com.example.apidemo.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.apidemo.R;
import com.example.apidemo.callback.ILoadmore;
import com.example.apidemo.callback.OnItemClickedListener;
import com.example.apidemo.data.ApiClient;
import com.example.apidemo.data.Constant;
import com.example.apidemo.data.RequestApi;
import com.example.apidemo.models.Hit;
import com.example.apidemo.models.ImageInfo;

import java.util.Collections;
import java.util.MissingResourceException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickedListener, ILoadmore {

    private static final String TAG = "__MainActivity";
    private static final int SIZE_MAX = 50;
    private static final int SIZE_GRID = 2;

    private RecyclerView mRecyclerview;
    private ImageAdapter mAdapter;

    private RequestApi mCallApi;

    private int currentPage = 1;
    private Boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();
        setViews();
        setListeners();
    }

    private void initialization() {
        mCallApi = ApiClient.getClient().create(RequestApi.class);
        mAdapter = new ImageAdapter(Collections.emptyList(), this, this);

        mRecyclerview = findViewById(R.id.recyclerView);
    }

    private void setViews() {
        mRecyclerview.setLayoutManager(new GridLayoutManager(this, SIZE_GRID));
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setAdapter(mAdapter);
    }

    private void setListeners() {
        recallApi(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            mRecyclerview.setOnScrollChangeListener((v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (!isLoading && mRecyclerview.canScrollVertically(1)) {
                    onLoadMore();
                    isLoading = true;
                }
            });
        }
    }

    private void recallApi(Boolean isLoadMore) {
        mCallApi.getAll(Constant.KEY, "photo", "", currentPage++)
                .enqueue(new Callback<ImageInfo>() {
                    @Override
                    public void onResponse(@NonNull Call<ImageInfo> call, @NonNull Response<ImageInfo> response) {
                        if (response.isSuccessful()) {
                            ImageInfo imageInfo = response.body();

                            if (isLoadMore) {
                                mAdapter.appendList(imageInfo.hits);

                                onStopLoadMore();
                            } else {
                                // update data
                                mAdapter.updateList(imageInfo.hits);
                                currentPage--;
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ImageInfo> call, @NonNull Throwable t) {
                        Log.d(TAG, "onFailure: ERORR = " + t.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void onItemClicked(Hit item) {
        Toast.makeText(this, "Clicked " + item.user, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadMore() {
        if (mAdapter.getItemCount() <= SIZE_MAX) {
            for (int i = 0; i < SIZE_GRID; i++) {
                mAdapter.appendItem(null);
            }

            new Handler().postDelayed(() -> {
                for (int i = 0; i < SIZE_GRID; i++) {
                    mAdapter.removeLastItem();
                }
                recallApi(true);

            }, 3000);
        }
    }

    @Override
    public void onStopLoadMore() {
        isLoading = false;
    }
}