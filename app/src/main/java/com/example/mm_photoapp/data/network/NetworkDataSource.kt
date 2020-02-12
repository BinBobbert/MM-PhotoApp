package com.example.mm_photoapp.data.network

import androidx.lifecycle.LiveData
import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.data.db.entities.Album

interface NetworkDataSource {
    val downloadedAlbums: LiveData<List<Album>>

    val downloadedAlbumPhotos: LiveData<List<Photo>>

    suspend fun getAlbums()

    suspend fun getAllPhotos()

    suspend fun getAlbumPhotos(albumId: Int)
}