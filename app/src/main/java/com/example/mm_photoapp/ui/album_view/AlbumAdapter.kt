package com.example.mm_photoapp.ui.album_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mm_photoapp.R
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.databinding.RecycleAlbumRowBinding

/**
 * Adapter for the Album RecycleViewer
 *
 * @param albumList = A list of Albums retrieved from 'https://jsonplaceholder.typicode.com/'
 * @param mOnAlbumListener = A listener for clicks on albums, in this case it is AlbumsActivity
 */
class AlbumAdapter(
    private val albumList: List<Album>,
    private val mOnAlbumListener: OnAlbumListener
): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){

    inner class AlbumViewHolder(val binding: RecycleAlbumRowBinding, private val onAlbumsListener: OnAlbumListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.albumRowContainer.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onAlbumsListener.onAlbumClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumViewHolder(
            DataBindingUtil.inflate<RecycleAlbumRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.recycle_album_row,
                parent,
                false
            ),
            mOnAlbumListener
        )

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.album = albumList[position]
    }

    interface OnAlbumListener{
        fun onAlbumClick(pos: Int)
    }
}


