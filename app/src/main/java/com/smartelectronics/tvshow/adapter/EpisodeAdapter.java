package com.smartelectronics.tvshow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.databinding.EpisodItemRowLayoutBinding;
import com.smartelectronics.tvshow.models.Episode;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>{

    private List<Episode> episodes;
    private LayoutInflater inflater;

    public EpisodeAdapter(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder{

        private EpisodItemRowLayoutBinding binding;

        public EpisodeViewHolder(EpisodItemRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Episode episode){
            String title = "S";
            String season = episode.getSeason();
            if(season.length() ==1){
                season = "0" . concat(season);
            }
            String episodeNumber = episode.getEpisode();
            if(episodeNumber.length() == 1){
                episodeNumber = "0" . concat(episodeNumber);
            }

            episodeNumber = "E" . concat(episodeNumber);
            title = title.concat(season).concat(episodeNumber);

            this.binding.setTitle(title);
            this.binding.setName(episode.getName());
            this.binding.setAirDate(episode.getAirDate());
        }
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        EpisodItemRowLayoutBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.episod_item_row_layout,
                parent,
                false
        );
        return new EpisodeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode currentEpisode = episodes.get(position);
        holder.bind(currentEpisode);
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }
}
