package com.example.mm_photoapp.internal

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("tasty")
fun loadThumbnail(view: ImageView, imageUrl: String){
    Picasso.get().load(imageUrl).into(view)
}