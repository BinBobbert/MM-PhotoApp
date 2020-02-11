package com.example.mm_photoapp.data.network

import android.util.Log
import com.example.mm_photoapp.data.network.response.Photo
import com.example.mm_photoapp.data.network.response.Album
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

}