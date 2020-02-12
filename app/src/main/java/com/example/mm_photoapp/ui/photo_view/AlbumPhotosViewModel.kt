package com.example.mm_photoapp.ui.photo_view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mm_photoapp.data.db.entities.Photo
import com.example.mm_photoapp.data.repository.PhotoAlbumRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class AlbumPhotosViewModel@Inject constructor(
    private val photoAbumRepo: @JvmSuppressWildcards PhotoAlbumRepo
): ViewModel() {

    private val _photolist = MutableLiveData<List<Photo>>()

    val photoList: LiveData<List<Photo>>
        get() = _photolist


    fun fetchData(albumId: Int){

        viewModelScope.launch {
            val photos = photoAbumRepo.getAlbumPhotos(albumId)
            _photolist.postValue(photos)
        }

    }

}