package com.example.mm_photoapp.internal

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import okhttp3.internal.wait
import java.lang.Exception

@BindingAdapter("tasty")
fun loadThumbnail(view: ImageView, imageUrl: String){

    Picasso.get().load(imageUrl).into(view)
}