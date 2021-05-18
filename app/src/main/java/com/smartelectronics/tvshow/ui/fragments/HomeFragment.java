package com.smartelectronics.tvshow.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.adapter.MostPopularTvShowAdapter;
import com.smartelectronics.tvshow.databinding.FragmentHomeBinding;

import com.smartelectronics.tvshow.viewModels.MostPopularTvShowViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MostPopularTvShowViewModel tvShowViewModel;

    private MostPopularTvShowAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tvShowViewModel = new ViewModelProvider(this).get(MostPopularTvShowViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.setLifecycleOwner(this);

        setHasOptionsMenu(true);
        initRecyclerView();

        Log.i("cycle", "onCreateView: ");
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tv_show_menu, menu);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("cycle", "onDestroy: ");
    }

    private void initRecyclerView(){
        binding.tvShowsRecyclerView.setHasFixedSize(true);
        adapter = new MostPopularTvShowAdapter(tvShowViewModel.tvShowItemsList);
        binding.tvShowsRecyclerView.setAdapter(adapter);
        binding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!binding.tvShowsRecyclerView.canScrollVertically(1)){
                    tvShowViewModel.increaseCurrentPage();
                    getMostPopularTvShows(tvShowViewModel.getCurrentPage());
                }
            }
        });

        if(tvShowViewModel.isAllowRefreshList()) {
            getMostPopularTvShows(tvShowViewModel.getCurrentPage());
            tvShowViewModel.setAllowRefreshList(false);
        }
    }

    private void toggleLoading(){
        if (tvShowViewModel.getCurrentPage() == 1){
            if(binding.getIsLoading() != null && binding.getIsLoading()){
                binding.setIsLoading(false);
            } else {
                binding.setIsLoading(true);
            }
        }else {
            if(binding.getIsLoadingMore() != null && binding.getIsLoadingMore()){
                binding.setIsLoadingMore(false);
                binding.progressMore.hide();
            } else {
                binding.setIsLoadingMore(true);
                binding.progressMore.show();
            }
        }
    }

    private void getMostPopularTvShows(int currentPage){
        toggleLoading();
        tvShowViewModel.getMostPopularTvShows(currentPage).observe(getViewLifecycleOwner(), popularTvShow -> {
            toggleLoading();
            if(popularTvShow != null){
                tvShowViewModel.setTotalAvailablePages(popularTvShow.getPages());
                Log.i("home", "getMostPopularTvShows: " +popularTvShow.getPages());
                if(popularTvShow.getTvShows() != null){
                    int oldCount = tvShowViewModel.tvShowItemsList.size();
                    tvShowViewModel.tvShowItemsList.addAll(popularTvShow.getTvShows());
                    adapter.notifyItemRangeInserted(oldCount, tvShowViewModel.tvShowItemsList.size());
                }
            }
        });
    }
}