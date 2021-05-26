package com.smartelectronics.tvshow.ui.fragments.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.adapter.PopularTvShowAdapter;
import com.smartelectronics.tvshow.databinding.FragmentSearchBinding;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.viewModels.SearchViewModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding;
    private SearchViewModel viewModel;

    private PopularTvShowAdapter adapter;

    private Timer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        binding.setLifecycleOwner(this);

        init();
        return binding.getRoot();
    }


    private void initImageView(){

        binding.imgBack.setOnClickListener(click ->{
            Navigation.findNavController(binding.getRoot()).navigateUp();
        });
    }

    private void initRecyclerView(){
        binding.searchRecyclerView.setHasFixedSize(true);
        adapter = new PopularTvShowAdapter(viewModel.tvShowItemsList, "search");
        binding.searchRecyclerView.setAdapter(adapter);


    }

    private void toggleLoading(){
        if (viewModel.getCurrentPage() == 1){
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

    private void searchTvShow(String query){
        toggleLoading();
        viewModel.searchTvShow(query, viewModel.getCurrentPage()).observe(this, result->{
            toggleLoading();
            if(result != null){
                viewModel.setTotalAvailablePages(result.getPages());
                if(result.getTvShows() != null){
                    int oldCount = viewModel.tvShowItemsList.size();
                    viewModel.tvShowItemsList.addAll(result.getTvShows());
                    adapter.notifyItemRangeInserted(oldCount, viewModel.tvShowItemsList.size());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void init(){

        initImageView();
        initRecyclerView();

        binding.inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(timer != null){
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().trim().isEmpty()){
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                viewModel.setCurrentPage(1);
                                viewModel.setTotalAvailablePages(1);
                                searchTvShow(s.toString());
                            });
                        }
                    }, 800);
                }else {
                    viewModel.tvShowItemsList.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        binding.searchRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!binding.searchRecyclerView.canScrollVertically(1)){
                    if(!binding.inputSearch.getText().toString().isEmpty()){
                        viewModel.increaseCurrentPage();
                        searchTvShow(binding.inputSearch.getText().toString());
                    }
                }
            }
        });
        binding.inputSearch.requestFocus();
    }

}