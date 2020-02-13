package com.example.mm_photoapp.ui.photo_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mm_photoapp.R
import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.databinding.RecyclePhotoRowBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.MainScope
import okhttp3.internal.wait

class PhotosAdapter(
    private val photoList: List<Photo>,
    private val mOnPhotoListener: OnPhotoListener
): RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    inner class PhotoViewHolder(val binding: RecyclePhotoRowBinding, private val onPhotoListener: OnPhotoListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        init {
            binding.photoContainer.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onPhotoListener.onPhotoClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            DataBindingUtil.inflate<RecyclePhotoRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recycle_photo_row,
                parent,
                false
            ),
            mOnPhotoListener
        )

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.binding.photo = photoList[position]
    }

    interface OnPhotoListener{
        fun onPhotoClick(pos: Int)
    }
}