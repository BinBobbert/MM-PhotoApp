package com.example.mm_photoapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.mm_photoapp.data.network.NetworkDataSource
import com.example.mm_photoapp.data.network.response.Album
import com.example.mm_photoapp.data.network.response.Photo
import com.example.mm_photoapp.di.ApplicationModule.NetworkDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * The repository decides where data is fetched from, remote or local.
 *
 */
class PhotoAlbumRepoImpl @Inject constructor(
    @NetworkDataSourceImpl private val networkDataSource: @JvmSuppressWildcards NetworkDataSource
) : PhotoAlbumRepo {

    private val _returnAlbums = MutableLiveData<List<Album>>()

    override val returnAlbums: LiveData<List<Album>>
        get() = _returnAlbums

    init {
        networkDataSource.downloadedAlbums.observeForever{
            _returnAlbums.postValue(it)
        }
    }

    override suspend fun fetchAlbums(){
        networkDataSource.getAlbums()
    }

    override suspend fun getAlbumPhotos(albumId: Int): List<Photo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}