package com.smartelectronics.tvshow.bindingAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.smartelectronics.tvshow.models.TvShow;
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
    public static void onTvShowClick(ConstraintLayout container, TvShow tvShow){
        container.setOnClickListener(v -> {
            NavDirections action = HomeFragmentDirections.actionHomeFragmentToTvShowDetailsFragment(tvShow);
            Navigation.findNavController(container).navigate(action);
        });
    }

    @BindingAdapter("android:parsHtml")
    public static void parsHtml(TextView textView, String description){
        if(description !=null) {
            textView.setText(
                    String.valueOf(
                            HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                    )
            );
            textView.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter("android:parsGenre")
    public static void movieGenreParser(TextView textView, String[] genre){
        if(genre != null){
            String genreParsed = "";
            for(int i=0; i< genre.length; i++){
                genreParsed += genre[i] + " | ";
            }
            textView.setText(genreParsed);
        }
    }
}
