package com.example.mm_photoapp.data.network

import androidx.lifecycle.LiveData
import com.example.mm_photoapp.data.network.response.Photo
import com.example.mm_photoapp.data.network.response.Album

interface NetworkDataSource {
    val downloadedAlbums: LiveData<List<Album>>

    val downloadedAlbumPhotos: LiveData<List<Photo>>

    suspend fun getAlbums()

    suspend fun getAlbumPhotos(albumId: Int)
}