package com.smartelectronics.tvshow.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.viewModels.MostPopularTvShowViewModel;

public class HomeFragment extends Fragment {

    private MostPopularTvShowViewModel tvShowViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvShowViewModel = new ViewModelProvider(requireActivity()).get(MostPopularTvShowViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true);
        getMostPopularTvShows();
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tv_show_menu, menu);
    }

    private void getMostPopularTvShows(){
        tvShowViewModel.getMostPopularTvShows(0).observe(getViewLifecycleOwner(), tvShow -> {
            Toast.makeText(getContext(), "Total pages " + tvShow.getPages(), Toast.LENGTH_SHORT).show();
        });
    }
}