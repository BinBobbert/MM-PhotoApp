package com.example.mm_photoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.mm_photoapp.data.network.response.Album
import com.example.mm_photoapp.data.network.response.Photo

interface PhotoAlbumRepo {
    val returnAlbums: LiveData<List<Album>>

    suspend fun fetchAlbums()

    suspend fun getAlbumPhotos(albumId: Int): List<Photo>
}