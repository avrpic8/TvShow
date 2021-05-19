package com.smartelectronics.tvshow.ui.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
        binding.setTvShowDetails(detailsViewModel);
        binding.setLifecycleOwner(this);

        TvShow result = TvShowDetailsFragmentArgs.fromBundle(getArguments()).getTvshow();
        Log.i("detail", "onCreateView: " + result.getId());

        init();

        /// Request data from server by tvShow id
        detailsViewModel.getTvShowDetails(result.getId());
        detailsViewModel.tvShowDetailsResponse.observe(requireActivity(), tvShowDetailsResponse -> {
            toggleLoading();
            if(tvShowDetailsResponse.getTvShowDetails() != null){
                if(tvShowDetailsResponse.getTvShowDetails().getPictures() != null){
                    if(detailsViewModel.isAllowRefreshList()) {
                        initImageSlider(tvShowDetailsResponse.getTvShowDetails().getPictures());
                        detailsViewModel.setAllowRefreshList(false);
                    }
                }
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
        /*initSliderIndicator(pictures.length);
        binding.sliderViewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                initCurrentSliderIndicator(position);
            }
        });*/
    }
    private void initImageView(){
        binding.backImageView.setOnClickListener(v -> {
            Navigation.findNavController(binding.mainContainer).navigateUp();
        });
    }
    private void init(){
        toggleLoading();
        initImageView();
    }

    /*private void initSliderIndicator(int count){
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for(int i=0; i< indicators.length; i++){
            indicators[i] = new ImageView(requireActivity());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getContext(),
                    R.drawable.background_slider_indicator_inactive)
            );
            indicators[i].setLayoutParams(layoutParams);
            binding.layoutSliderIndicator.addView(indicators[i]);
        }
        binding.layoutSliderIndicator.setVisibility(View.VISIBLE);
        initCurrentSliderIndicator(0);
    }

    private void initCurrentSliderIndicator(int position){
        int childCount = binding.layoutSliderIndicator.getChildCount();
        for(int i=0; i<childCount; i++){
            ImageView imageView = (ImageView) binding.layoutSliderIndicator.getChildAt(i);
            if(i == position){
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(), R.drawable.background_slider_indicator_active
                ));
            }else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getContext(), R.drawable.background_slider_indicator_inactive
                ));
            }
        }
    }
*/
}