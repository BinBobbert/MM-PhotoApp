package com.example.mm_photoapp.ui.album_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mm_photoapp.R
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.databinding.RecycleAlbumRowBinding

/**
 * Adapter for the Album RecycleViewer
 * Makes use of Data Binding
 * Only inflates relevant Views for better performance
 */
class AlbumAdapter(
    private val albumList: List<Album>
): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){

    inner class AlbumViewHolder(val binding: RecycleAlbumRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(
            DataBindingUtil.inflate<RecycleAlbumRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recycle_album_row,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.album = albumList[position]
    }
}


