package com.smartelectronics.tvshow.ui.fragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.adapter.PopularTvShowAdapter;
import com.smartelectronics.tvshow.databinding.FragmentHomeBinding;
import com.smartelectronics.tvshow.viewModels.MostPopularTvShowViewModel;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MostPopularTvShowViewModel tvShowViewModel;

    private PopularTvShowAdapter adapter;


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
        initImageViews();
        initRecyclerView();
        initRefreshLayout();

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitDialog();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);

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

    private void initImageViews() {
        binding.watchListImageView.setOnClickListener(click -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToWatchListFragment();
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });

        binding.searchImageView.setOnClickListener(click -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToSearchFragment();
            Navigation.findNavController(binding.getRoot()).navigate(action);
        });
    }

    private void initRecyclerView() {
        binding.tvShowsRecyclerView.setHasFixedSize(true);
        adapter = new PopularTvShowAdapter(tvShowViewModel.tvShowItemsList, "home");
        binding.tvShowsRecyclerView.setAdapter(adapter);
        binding.tvShowsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!binding.tvShowsRecyclerView.canScrollVertically(1)) {
                    tvShowViewModel.increaseCurrentPage();
                    getMostPopularTvShows(tvShowViewModel.getCurrentPage());
                }
            }
        });

        if (tvShowViewModel.isAllowRefreshList()) {
            getMostPopularTvShows(tvShowViewModel.getCurrentPage());
            tvShowViewModel.setAllowRefreshList(false);
        }
    }

    private void toggleLoading() {
        if (tvShowViewModel.getCurrentPage() == 1) {
            if (binding.getIsLoading() != null && binding.getIsLoading()) {
                binding.setIsLoading(false);
            } else {
                binding.setIsLoading(true);
            }
        } else {
            if (binding.getIsLoadingMore() != null && binding.getIsLoadingMore()) {
                binding.setIsLoadingMore(false);
                binding.progressMore.hide();
            } else {
                binding.setIsLoadingMore(true);
                binding.progressMore.show();
            }
        }
    }

    private void showError() {
        binding.imgError.setVisibility(View.VISIBLE);
        binding.txtError.setVisibility(View.VISIBLE);
        binding.tvShowsRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void hideError() {
        binding.imgError.setVisibility(View.GONE);
        binding.txtError.setVisibility(View.GONE);
        binding.tvShowsRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Exit app")
                .setMessage(R.string.exit_message)
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.cancel();
                    getActivity().finish();
                })
                .setNegativeButton("No", ((dialog, which) -> {
                    dialog.cancel();
                }));
        builder.show();
    }

    private void initRefreshLayout() {
        binding.refreshLayout.setColorScheme(R.color.blue,
                R.color.green, R.color.orange, R.color.purple);

        binding.refreshLayout.setOnRefreshListener(() -> {
            tvShowViewModel.tvShowItemsList.clear();
            getMostPopularTvShows(1);
        });
    }

    private void getMostPopularTvShows(int currentPage) {
        toggleLoading();
        tvShowViewModel.getMostPopularTvShows(currentPage).observe(getViewLifecycleOwner(), popularTvShow -> {
            toggleLoading();
            if (popularTvShow != null) {
                hideError();
                binding.refreshLayout.setRefreshing(false);
                tvShowViewModel.setTotalAvailablePages(popularTvShow.getPages());
                Log.i("home", "getMostPopularTvShows: " + popularTvShow.getPages());
                if (popularTvShow.getTvShows() != null) {
                    int oldCount = tvShowViewModel.tvShowItemsList.size();
                    tvShowViewModel.tvShowItemsList.addAll(popularTvShow.getTvShows());
                    adapter.notifyItemRangeInserted(oldCount, tvShowViewModel.tvShowItemsList.size());
                    adapter.notifyDataSetChanged();
                }
            } else {
                binding.refreshLayout.setRefreshing(false);
                showError();
            }
        });
    }
}