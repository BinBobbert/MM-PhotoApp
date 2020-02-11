package com.example.mm_photoapp.data.repository

import com.example.mm_photoapp.data.db.AlbumDao
import com.example.mm_photoapp.data.network.NetworkDataSource
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.di.ApplicationModule.NetworkDataSourceInj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * The repository decides where data is fetched from, remote or local.
 *
 */
class PhotoAlbumRepoImpl @Inject constructor(
    @NetworkDataSourceInj private val networkDataSource: @JvmSuppressWildcards NetworkDataSource,
    private val albumDao: AlbumDao
) : PhotoAlbumRepo {

    init {
        networkDataSource.downloadedAlbums.observeForever{
            persistFetchedAlbums(it)
        }
    }

    /**
     * Initizalize remote data
     * And return List<Albums> from the db
     */
    override suspend fun fetchAlbums(): List<Album>{
        return withContext(Dispatchers.IO){
            initData()
            return@withContext albumDao.getAllAlbums()
        }
    }

    override suspend fun getAlbumPhotos(albumId: Int): List<Photo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * Save fetchedAlbums to the db
     */
    private fun persistFetchedAlbums(fetchedAlbums: List<Album>){
        GlobalScope.launch(Dispatchers.IO) {
            albumDao.upsert(fetchedAlbums)
        }

    }

    /**
     * TODO: Add a way to determine whether data needs to be initialized.
     * networDataSource.downloadedAlbums has an observer attached to it
     * If there are changes the data is persisted into the db
     */
    private suspend fun initData(){
        networkDataSource.getAlbums()
    }
}