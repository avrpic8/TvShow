package com.smartelectronics.tvshow.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.adapter.ImageSliderAdapter;
import com.smartelectronics.tvshow.databinding.FragmentTvShowDetailsBinding;
import com.smartelectronics.tvshow.models.TvShow;
import com.smartelectronics.tvshow.models.TvShowDetails;
import com.smartelectronics.tvshow.responses.TvShowDetailsResponse;
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
        binding.setResult(result);
        binding.setTvShowDetails(detailsViewModel);

        init();

        /// Request data from server by tvShow id
        detailsViewModel.getTvShowDetails(result.getId());
        detailsViewModel.tvShowDetailsResponse.observe(requireActivity(), tvShowDetailsResponse -> {
            toggleLoading();
            if(tvShowDetailsResponse.getTvShowDetails() != null){
                if(tvShowDetailsResponse.getTvShowDetails().getPictures() != null){
                    initImageSlider(tvShowDetailsResponse.getTvShowDetails().getPictures());
                }

                loadDataToViews(tvShowDetailsResponse);
            }
        });

        return binding.getRoot();
    }


    private void toggleLoading(){
        if(binding.getIsLoading() != null && binding.getIsLoading()) binding.setIsLoading(false);
        else binding.setIsLoading(true);
    }

    private void initImageSlider(String[] pictures){
        binding.sliderViewpager.setOffscreenPageLimit(1);
        binding.sliderViewpager.setAdapter(new ImageSliderAdapter(pictures));
        binding.sliderViewpager.setVisibility(View.VISIBLE);
        binding.viewFadingEdge.setVisibility(View.VISIBLE);
        binding.tabLayout.setVisibility(View.VISIBLE);
        new TabLayoutMediator(binding.tabLayout, binding.sliderViewpager, ((tab, position) -> tab.select())).attach();
    }

    private void initImageView(){
        binding.backImageView.setOnClickListener(v -> {
            Navigation.findNavController(binding.mainContainer).navigateUp();
        });
    }

    private void loadDataToViews(TvShowDetailsResponse response){
        binding.txtReadMore.setVisibility(View.VISIBLE);
        binding.txtReadMore.setOnClickListener(v -> {
            if(binding.txtReadMore.getText().toString().equals("Read more")){
                binding.txtDescription.setMaxLines(Integer.MAX_VALUE);
                binding.txtDescription.setEllipsize(null);
                binding.txtReadMore.setText(R.string.read_less);

            }else {
                binding.txtDescription.setMaxLines(4);
                binding.txtDescription.setEllipsize(TextUtils.TruncateAt.END);
                binding.txtReadMore.setText(R.string.read_more);
            }
        });

        if(response.getTvShowDetails().getGenres() != null){
            binding.txtGenre.setText(response.getTvShowDetails().getGenres()[0]);
        }else{
            binding.txtGenre.setText("N/A");
        }
        binding.divider1.setVisibility(View.VISIBLE);
        binding.layoutMisc.setVisibility(View.VISIBLE);
        binding.divider2.setVisibility(View.VISIBLE);

        /// buttons
        binding.btnWebsite.setOnClickListener(click ->{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(response.getTvShowDetails().getUrl()));
            startActivity(intent);
        });
        binding.btnWebsite.setVisibility(View.VISIBLE);
        binding.btnEpisodes.setVisibility(View.VISIBLE);

    }

    private void init(){
        toggleLoading();
        initImageView();
    }


}