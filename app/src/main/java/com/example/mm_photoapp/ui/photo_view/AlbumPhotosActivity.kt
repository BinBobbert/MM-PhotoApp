package com.example.mm_photoapp.ui.photo_view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mm_photoapp.R
import com.example.mm_photoapp.data.db.entities.Photo
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_album_photos.*
import javax.inject.Inject

/**
 * This activity allows users to see a list of photos
 * The list of photos is specified by an album
 */
class AlbumPhotosActivity : DaggerAppCompatActivity(), PhotosAdapter.OnPhotoListener{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AlbumPhotosViewModel> {viewModelFactory}

    private var recyclerPhotos  = mutableListOf<Photo>()

    // Boolean to determine whether there is an active fragment
    private var activeFrag = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_photos)
        setSupportActionBar(toolbar)
        recycler_photos.visibility = View.GONE

        val albumId = intent.getIntExtra(getString(R.string.album_id), 1)
        val fetchedPhotos = viewModel.photoList

        viewModel.fetchData(albumId)

        fetchedPhotos.observe(this@AlbumPhotosActivity, Observer {

            recyclerPhotos.addAll(it)

            // initialize recycleViewer
            recycler_photos.also {rec ->
                rec.layoutManager = GridLayoutManager(this@AlbumPhotosActivity, 2)
                val photosAdapter = PhotosAdapter(recyclerPhotos, this@AlbumPhotosActivity)
                rec.adapter = photosAdapter

                rec.visibility = View.VISIBLE

            }
        })
    }

    /**
     * Called when the user clicks on a photo
     * Starts up a fragment and switches layout visibility
     */
    override fun onPhotoClick(pos: Int) {
        val photo = recyclerPhotos[pos]

        val photoDetailFragment = PhotoDetail.newInstance(photo.url, photo.title)
        val manager = supportFragmentManager

        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        fragmentTransaction.add(R.id.fragment_container, photoDetailFragment)
        fragmentTransaction.commit()

        recycler_photos.visibility = View.GONE
        fragment_container.visibility = View.VISIBLE
        activeFrag = true

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    /**
     * Overriding onBackPressed so that we can back out of the fragment
     * without going back to the first activity
     */
    override fun onBackPressed() {
        if (!activeFrag){
            activeFrag = true
            finish()
        } else {
            onResume()
            activeFrag = false
        }
    }

    /**
     * onResume the layouts are switched back
     */
    override fun onResume() {
        super.onResume()
        fragment_container.visibility = View.GONE
        recycler_photos.visibility = View.VISIBLE
    }

}
