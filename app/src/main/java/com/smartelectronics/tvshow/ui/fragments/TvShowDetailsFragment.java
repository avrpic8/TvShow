package com.smartelectronics.tvshow.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.databinding.FragmentTvShowDetailsBinding;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.viewModels.TvShowDetailsViewModel;

public class TvShowDetailsFragment extends Fragment {

    private FragmentTvShowDetailsBinding binding;
    private TvShowDetailsViewModel detailsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        detailsViewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show_details, container, false);
        binding.setLifecycleOwner(this);

        TvShow result = TvShowDetailsFragmentArgs.fromBundle(getArguments()).getTvshow();
        Log.i("detail", "onCreateView: " + result.getId());
        getTvShowDetails(result.getId());

        return binding.getRoot();
    }

    private void getTvShowDetails(int tvShowId){
        binding.setIsLoading(true);
        detailsViewModel.getTvShowDetails(tvShowId).observe(getViewLifecycleOwner(), tvShowDetails -> {
            binding.setIsLoading(false);
            Toast.makeText(requireContext(), tvShowDetails.getTvShowDetails().getUrl(), Toast.LENGTH_SHORT).show();
        });
    }


}