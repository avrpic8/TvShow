package com.smartelectronics.tvshow.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.databinding.TvShowRowLayoutBinding;
import com.smartelectronics.tvshow.models.MostPopularTvShowItems;

import java.util.ArrayList;
import java.util.List;

public class MostPopularTvShowAdapter extends RecyclerView.Adapter<MostPopularTvShowAdapter.TvShowViewHolder> {

    private List<MostPopularTvShowItems> tvShowsItems = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public static class TvShowViewHolder extends RecyclerView.ViewHolder {

        private TvShowRowLayoutBinding binding;

        public TvShowViewHolder(TvShowRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MostPopularTvShowItems tvShowsItem){
            this.binding.setTvShowItem(tvShowsItem);
            this.binding.executePendingBindings();
        }
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        TvShowRowLayoutBinding binding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.tv_show_row_layout,
                parent,
                false
        );
        return new TvShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        MostPopularTvShowItems currentTvShow = tvShowsItems.get(position);
        holder.bind(currentTvShow);
    }

    @Override
    public int getItemCount() {
        return tvShowsItems.size();
    }

    public void setData(List<MostPopularTvShowItems> items){
        int oldCount = this.tvShowsItems.size();
        this.tvShowsItems.addAll(items);
        notifyItemRangeInserted(oldCount, this.tvShowsItems.size());
    }
}
