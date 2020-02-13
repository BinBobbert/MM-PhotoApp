package com.example.mm_photoapp.data.repository

import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.data.db.entities.Photo

interface PhotoAlbumRepo {
    suspend fun fetchAlbums(): List<Album>

    suspend fun getAlbumPhotos(albumId: Int): List<Photo>
}