package com.example.mm_photoapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.data.db.entities.Album

import java.lang.Exception
import javax.inject.Inject

/**
 * Remote data source
 * Uses retrofit to retrieve API data
 */
class NetworkDataSourceImpl @Inject constructor(
    private val photoApi: @JvmSuppressWildcards PhotoAPI
) : NetworkDataSource {

    private val _downloadedAlbums = MutableLiveData<List<Album>>()
    private val _downloadedAlbumPhotos = MutableLiveData<List<Photo>>()

    override val downloadedAlbums: LiveData<List<Album>>
        get() = _downloadedAlbums

    override val downloadedAlbumPhotos: LiveData<List<Photo>>
        get() = _downloadedAlbumPhotos

    override suspend fun getAlbums(){
        try{
            val fetchedAlbums: List<Album> =
                photoApi.getAlbums()
            Log.d("chris", fetchedAlbums.toString())

            _downloadedAlbums.postValue(fetchedAlbums)
        } catch(e: Exception) {
            Log.e("error", e.toString())
        }
    }

    override suspend fun getAlbumPhotos(albumId: Int){
        try {
            val fetchedAlbumPhotos: List<Photo> =
                photoApi.getAlbumPhotos(albumId)

            _downloadedAlbumPhotos.postValue(fetchedAlbumPhotos)
        } catch (e: Exception) {
            Log.e("error", e.toString())
        }
    }
}