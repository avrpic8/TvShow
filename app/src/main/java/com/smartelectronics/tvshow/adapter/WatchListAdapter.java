package com.smartelectronics.tvshow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.RecyclerView;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.databinding.TvShowRowLayoutBinding;
import com.smartelectronics.tvshow.listener.WatchListListener;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.ui.fragments.home.HomeFragmentDirections;
import com.smartelectronics.tvshow.ui.fragments.watchlist.WatchListFragmentDirections;

import java.util.List;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.TvShowViewHolder> {

    private List<TvShow> watchList;
    private LayoutInflater layoutInflater;
    private WatchListListener watchListListener;

    public WatchListAdapter(List<TvShow> tvShow, WatchListListener listener) {
        watchListListener = listener;
        watchList = tvShow;
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        private TvShowRowLayoutBinding binding;

        public TvShowViewHolder(TvShowRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(TvShow tvShowsItem){
            this.binding.setTvShowItem(tvShowsItem);
            this.binding.executePendingBindings();
            binding.container.setOnClickListener(v -> {
                binding.dataLoading.setVisibility(View.VISIBLE);
                NavDirections action = WatchListFragmentDirections.actionWatchListFragmentToTvShowDetailsFragment(tvShowsItem);
                Navigation.findNavController(binding.tvShowImageView).navigate(action);
            });
            binding.imgDelete.setVisibility(View.VISIBLE);
            binding.imgDelete.setOnClickListener(click ->{
                watchListListener.removeTvShowFromWatchList(tvShowsItem, getBindingAdapterPosition());
            });
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
        TvShow currentTvShow = watchList.get(position);
        holder.bind(currentTvShow);
    }

    @Override
    public int getItemCount() {
        return watchList.size();
    }
}
