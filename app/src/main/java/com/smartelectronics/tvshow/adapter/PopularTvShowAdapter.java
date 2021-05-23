package com.smartelectronics.tvshow.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.RecyclerView;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.databinding.TvShowRowLayoutBinding;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.ui.fragments.HomeFragmentDirections;

import java.util.List;

public class PopularTvShowAdapter extends RecyclerView.Adapter<PopularTvShowAdapter.TvShowViewHolder> {

    private List<TvShow> tvShowsItems;
    private LayoutInflater layoutInflater;

    public PopularTvShowAdapter(List<TvShow> tvShowsItems) {
        this.tvShowsItems = tvShowsItems;
    }

    public static class TvShowViewHolder extends RecyclerView.ViewHolder {

        private TvShowRowLayoutBinding binding;

        public TvShowViewHolder(TvShowRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(TvShow tvShowsItem){
            this.binding.setTvShowItem(tvShowsItem);
            this.binding.executePendingBindings();
            binding.container.setOnClickListener(v -> {
                binding.tvShowImageView.setTransitionName("imgCover");
                binding.nameTextView.setTransitionName("titleCover");
                binding.networkTextView.setTransitionName("networkCover");
                binding.statusTextView.setTransitionName("statusCover");
                binding.startedTextView.setTransitionName("startedCover");

                FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder().
                    addSharedElement(binding.tvShowImageView, "imgCover").
                    addSharedElement(binding.nameTextView, "titleCover").
                    addSharedElement(binding.networkTextView, "networkCover").
                    addSharedElement(binding.statusTextView, "statusCover").
                    addSharedElement(binding.startedTextView, "startedCover").build();
                NavDirections action = HomeFragmentDirections.actionHomeFragmentToTvShowDetailsFragment(tvShowsItem);
                Navigation.findNavController(binding.tvShowImageView).navigate(action, extras);
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
        TvShow currentTvShow = tvShowsItems.get(position);
        holder.bind(currentTvShow);
    }

    @Override
    public int getItemCount() {
        return tvShowsItems.size();
    }

    public void setData(List<TvShow> items){
        this.tvShowsItems = items;
        notifyDataSetChanged();
    }
}
