package com.smartelectronics.tvshow.ui.fragments.watchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.adapter.WatchListAdapter;
import com.smartelectronics.tvshow.databinding.FragmentWatchListBinding;
import com.smartelectronics.tvshow.listener.WatchListListener;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.viewModels.WatchListViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WatchListFragment extends Fragment implements LifecycleObserver, WatchListListener {

    private FragmentWatchListBinding binding;
    private WatchListViewModel viewModel;
    private WatchListAdapter adapter;
    private List<TvShow> watchList = new ArrayList<>();
    private int position = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(WatchListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_watch_list,
                container,
                false
        );
        binding.setLifecycleOwner(this);

        viewModel.toast.observe(requireActivity(), toast -> {
            if (viewModel.getShowToast()) {
                Toast.makeText(requireContext(), toast, Toast.LENGTH_SHORT).show();
                viewModel.setShowToast(false);
            }
        });
        viewModel.dataReady.observe(requireActivity(), dataReady -> {
            if (dataReady) {
                binding.favoriteList.setAdapter(adapter);
                viewModel.dataReady.setValue(false);
            }

        });
        viewModel.dataRemove.observe(requireActivity(), dataRemove -> {
            if (dataRemove) {
                adapter.notifyItemRemoved(position);
                adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                viewModel.dataRemove.setValue(false);
            }
        });

        initImageViews();


        loadWatchList();

        return binding.getRoot();
    }

    private void initImageViews() {
        binding.imgBack.setOnClickListener(click -> {
            Navigation.findNavController(binding.getRoot()).navigateUp();
        });
    }

    private void loadWatchList() {
        binding.setIsLoading(true);
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(viewModel.getWatchList()
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(tvShows -> {
                    binding.setIsLoading(false);
                    if (watchList.size() > 0) {
                        watchList.clear();
                    }
                    watchList.addAll(tvShows);
                    adapter = new WatchListAdapter(watchList, this);
                    binding.favoriteList.setVisibility(View.VISIBLE);
                    viewModel.dataReady.postValue(true);
                    disposable.dispose();
                })
        );
    }

    @Override
    public void removeTvShowFromWatchList(TvShow tvShow, int position) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(viewModel.removeFromWatchList(tvShow)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(() -> {
                    this.position = position;
                    watchList.remove(this.position);
                    viewModel.dataRemove.postValue(true);
                    disposable.dispose();
                })
        );

    }
}