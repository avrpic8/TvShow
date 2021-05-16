package com.smartelectronics.tvshow.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
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
import com.smartelectronics.tvshow.adapter.MostPopularTvShowAdapter;
import com.smartelectronics.tvshow.databinding.FragmentHomeBinding;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.models.TvShowsItem;
import com.smartelectronics.tvshow.viewModels.MostPopularTvShowViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private MostPopularTvShowViewModel tvShowViewModel;
    private FragmentHomeBinding binding;

    private List<TvShowsItem> tvShowsItems = new ArrayList<>();
    private MostPopularTvShowAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvShowViewModel = new ViewModelProvider(requireActivity()).get(MostPopularTvShowViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);

        setHasOptionsMenu(true);
        initRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tv_show_menu, menu);
    }

    private void initRecyclerView(){
        binding.tvShowsRecyclerView.setHasFixedSize(true);
        adapter = new MostPopularTvShowAdapter();
        binding.tvShowsRecyclerView.setAdapter(adapter);
        getMostPopularTvShows();
    }
    private void getMostPopularTvShows(){
        binding.setIsLoading(true);
        tvShowViewModel.getMostPopularTvShows(0).observe(getViewLifecycleOwner(), tvShow -> {
            binding.setIsLoading(false);
            if(tvShow != null){
                if(tvShow.getTvShows() != null){
                    adapter.setData(tvShow.getTvShows());
                }
            }
        });
    }
}