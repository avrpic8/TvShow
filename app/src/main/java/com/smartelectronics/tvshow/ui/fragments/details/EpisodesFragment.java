package com.smartelectronics.tvshow.ui.fragments.details;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.adapter.EpisodeAdapter;
import com.smartelectronics.tvshow.databinding.FragmentEpisodesBinding;
import com.smartelectronics.tvshow.models.TvShowDetails;
import com.smartelectronics.tvshow.viewModels.TvShowDetailsViewModel;


public class EpisodesFragment extends BottomSheetDialogFragment {

    private FragmentEpisodesBinding binding;
    private EpisodeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_episodes, container, false);
        binding.setLifecycleOwner(this);

        TvShowDetails details = EpisodesFragmentArgs.fromBundle(getArguments()).getTvDetail();
        binding.setDetails(details);

        initButtons();
        initRecyclerView(details);
        return binding.getRoot();
    }

    private void initButtons(){

        binding.imgClose.setOnClickListener(click ->{
            NavHostFragment.findNavController(this).navigateUp();
        });
    }

    private void initRecyclerView(TvShowDetails details){
        binding.episodeRecycler.setHasFixedSize(true);
        adapter = new EpisodeAdapter(details.getEpisodes());
        binding.episodeRecycler.setAdapter(adapter);
    }
}