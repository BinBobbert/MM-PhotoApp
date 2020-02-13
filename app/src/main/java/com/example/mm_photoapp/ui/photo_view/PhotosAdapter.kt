package com.example.mm_photoapp.ui.photo_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mm_photoapp.R
import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.databinding.RecyclePhotoRowBinding

/**
 * Adapter class for the RecycleViewer in AlbumPhotosActivity
 *
 * @param photoList = A list of photos belonging to an album
 * @param mOnPhotoListener = A listener for clicks on photos, in this case it is AlbumPhotosActivity
 */
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