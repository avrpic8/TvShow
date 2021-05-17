package com.smartelectronics.tvshow.bindingAdapter;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.BindingAdapter;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.smartelectronics.tvshow.R;
import com.smartelectronics.tvshow.models.MostPopularTvShow;
import com.smartelectronics.tvshow.models.MostPopularTvShowItems;
import com.smartelectronics.tvshow.ui.fragments.HomeFragment;
import com.smartelectronics.tvshow.ui.fragments.HomeFragmentDirections;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MostPopularTvShowBinding {

    @BindingAdapter("android:imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        try {
            imageView.setAlpha(0f);
            Picasso.get().load(url).noFade().into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.animate().setDuration(300).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }catch (Exception ignored){}
    }

    @BindingAdapter("tvShowClick")
    public static void onTvShowClick(ConstraintLayout container, MostPopularTvShowItems tvShow){
        container.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToTvShowDetailsFragment(tvShow);
            Navigation.findNavController(container).navigate(action);
        });
    }
}
