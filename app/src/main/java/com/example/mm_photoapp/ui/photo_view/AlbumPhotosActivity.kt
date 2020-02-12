package com.example.mm_photoapp.ui.photo_view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mm_photoapp.R
import com.example.mm_photoapp.data.db.entities.Photo
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_album_photos.*
import javax.inject.Inject


class AlbumPhotosActivity : DaggerAppCompatActivity(), PhotosAdapter.OnPhotoListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AlbumPhotosViewModel> {viewModelFactory}

    private var recyclerPhotos  = mutableListOf<Photo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_photos)
        setSupportActionBar(toolbar)
        recycler_photos.visibility = View.GONE
        progress_photo.visibility = View.VISIBLE

        val albumId = intent.getIntExtra("AlbumID", 1)
        val fetchedPhotos = viewModel.photoList

        viewModel.fetchData(albumId)



        fetchedPhotos.observe(this@AlbumPhotosActivity, Observer {

            recyclerPhotos.addAll(it)

            recycler_photos.also {
                it.layoutManager = GridLayoutManager(this@AlbumPhotosActivity, 2)
                val photosAdapter = PhotosAdapter(recyclerPhotos, this@AlbumPhotosActivity, this@AlbumPhotosActivity)
                it.adapter = photosAdapter

                recycler_photos.viewTreeObserver
                    .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            progress_photo.visibility = View.GONE
                            recycler_photos.visibility = View.VISIBLE
                            recycler_photos.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        }
                    })
            }
        })
    }

    override fun onPhotoClick(pos: Int) {
        Log.d("chris", " photo clicked")
    }

    override fun finish() {
        super.finish()

        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}
