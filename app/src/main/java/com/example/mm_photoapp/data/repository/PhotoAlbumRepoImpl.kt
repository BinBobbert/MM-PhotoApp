package com.example.mm_photoapp.data.repository

import com.example.mm_photoapp.data.db.PhotoAlbumDao
import com.example.mm_photoapp.data.network.NetworkDataSource
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.di.modules.ApplicationModule.NetworkDataSourceInj
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
    private val photoAlbumDao: PhotoAlbumDao
) : PhotoAlbumRepo {

    init {
        networkDataSource.downloadedAlbums.observeForever{
            persistFetchedAlbums(it)
        }

        networkDataSource.downloadedAlbumPhotos.observeForever{
            persistFetchedAlbumPhotos(it)
        }
    }

    /**
     * Initizalize remote data
     * And return List<Albums> from the db
     */
    override suspend fun fetchAlbums(): List<Album>{
        return withContext(Dispatchers.IO){
            initAlbumData()
            return@withContext photoAlbumDao.getAllAlbums()
        }
    }

    /**
     * Retrieves a list of photos belonging to an Album
     */
    override suspend fun getAlbumPhotos(albumId: Int): List<Photo> {
        return withContext(Dispatchers.IO){
            initAlbumPhotosData()
            return@withContext photoAlbumDao.getAlbumPhotos(albumId)
        }
    }

    /**
     * Save fetchedAlbums to the db
     */
    private fun persistFetchedAlbums(fetchedAlbums: List<Album>){
        GlobalScope.launch(Dispatchers.IO) {
            photoAlbumDao.upsertAlbums(fetchedAlbums)
        }
    }

    private fun persistFetchedAlbumPhotos(fetchedPhotos: List<Photo>){
        GlobalScope.launch(Dispatchers.IO) {
            photoAlbumDao.upsertPhotos(fetchedPhotos)
        }
    }

    /**
     * TODO: Add a way to determine whether data needs to be initialized.
     * networDataSource.downloadedAlbums has an observer attached to it
     * If there are changes the data is persisted into the db
     */
    private suspend fun initAlbumData(){
        networkDataSource.getAlbums()
    }

    private suspend fun initAlbumPhotosData(){
        networkDataSource.getAllPhotos()
    }
}