package com.example.apidemo.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apidemo.R;
import com.example.apidemo.callback.ILoadmore;
import com.example.apidemo.callback.OnItemClickedListener;
import com.example.apidemo.models.Hit;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOAD_MORE = 1;

    private List<Hit> mCurrentItems = new ArrayList<>();
    private final OnItemClickedListener mListener;
    private final ILoadmore mLoadmore;

    public ImageAdapter(@NonNull List<Hit> items, @NonNull OnItemClickedListener listener, @NonNull ILoadmore loadMore) {
        mCurrentItems = items;
        mListener = listener;
        mLoadmore = loadMore;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false)
            );
        } else {
            return new LoadingViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loadmore, parent, false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bindData(getItem(position));
        } else {
            ((LoadingViewHolder) holder).onLoading();
        }
    }

    @Override
    public int getItemCount() {
        return (mCurrentItems == null ? 0 : mCurrentItems.size());
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position) == null ? TYPE_LOAD_MORE : TYPE_ITEM;
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar progressLoadMore;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);

            progressLoadMore = itemView.findViewById(R.id.progressLoadMore);
        }

        public void onLoading() {
            progressLoadMore.setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final CircleImageView imageAvatar;
        private final TextView tvTitle;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageAvatar = itemView.findViewById(R.id.imgAvatar);
            tvTitle = itemView.findViewById(R.id.tvTitle);

        }

        public void bindData(Hit item) {
            itemView.setOnClickListener(v -> mListener.onItemClicked(item));


            Glide.with(itemView)
                    .load(item.webformatURL)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(imageAvatar);

            tvTitle.setText(item.user);
        }
    }

    public void updateList(@NonNull List<Hit> list) {
        mCurrentItems = new ArrayList<>(list);

        notifyDataSetChanged();
    }

    public void appendList(@NonNull List<Hit> list) {
        mCurrentItems.addAll(list);

        notifyItemRangeInserted(Math.max(mCurrentItems.size() - list.size(), 0), list.size());
    }

    public void appendItem(Hit item) {
        mCurrentItems.add(item);

        notifyItemInserted(mCurrentItems.size() -1);
    }
    
    public void removeLastItem() {
        int position = mCurrentItems.size() - 1;
        mCurrentItems.remove(position);

        notifyItemRemoved(position);
    }

    private Hit getItem(int position) {
        return (position < mCurrentItems.size() ? mCurrentItems.get(position) : null);
    }
}
