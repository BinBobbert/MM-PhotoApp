package com.example.mm_photoapp.internal

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception


/**
 *  Loads an image into an ImageView using Picasso
 *  ImageView becomes visible once picasso loads successfully
 */
@BindingAdapter("tasty")
fun loadThumbnail(view: ImageView, imageUrl: String){
    Picasso.get().load(imageUrl).into(view, object: Callback{
        override fun onSuccess() {
            view.visibility = View.VISIBLE
        }

        override fun onError(e: Exception?) {
            Log.e("error", e.toString())
        }

    })

}