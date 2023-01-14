package com.example.vutvapplication;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingUtils {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl){
        Picasso.get().load(imageUrl).into(view);
    }
}
