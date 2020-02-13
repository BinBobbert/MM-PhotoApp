package com.example.mm_photoapp.ui.album_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.data.repository.PhotoAlbumRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  AlbumsViewModel holds the data for AlbumsActivity
 */
class AlbumsViewModel @Inject constructor(
    private val photoAbumRepo: @JvmSuppressWildcards PhotoAlbumRepo
): ViewModel(){

    private val _albumList = MutableLiveData<List<Album>>()

    val albumList: LiveData<List<Album>>
        get() = _albumList

    fun fetchAlbums() {

        viewModelScope.launch {
            val albums = photoAbumRepo.fetchAlbums()
            _albumList.postValue(albums)
        }
    }
}