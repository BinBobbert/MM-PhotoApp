package com.example.mm_photoapp.data.network

import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.data.db.entities.Album
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API Service
 * Defines the API calls that are made in the project
 *
 */
interface PhotoAPI{

    @GET("/albums")
    suspend fun getAlbums(): List<Album>

    @GET("/photos")
    suspend fun getAlbumPhotos(@Query("id") albumId: Int): List<Photo>

    @GET("/photos")
    suspend fun getAllPhotos(): List<Photo>

}