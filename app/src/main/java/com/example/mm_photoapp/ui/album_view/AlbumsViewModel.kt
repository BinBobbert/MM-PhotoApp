package com.example.mm_photoapp.ui.album_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mm_photoapp.data.network.response.Album
import com.example.mm_photoapp.data.repository.PhotoAlbumRepo
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(
    private val photoAbumRepo: @JvmSuppressWildcards PhotoAlbumRepo
): ViewModel(){

    private val _albumList = MutableLiveData<List<Album>>()

    val albumList: LiveData<List<Album>>
        get() = _albumList

    fun fetchAlbums() {

        viewModelScope.launch {
            photoAbumRepo.fetchAlbums()

            photoAbumRepo.returnAlbums.observeForever{
                _albumList.postValue(it)
            }

        }
    }
}