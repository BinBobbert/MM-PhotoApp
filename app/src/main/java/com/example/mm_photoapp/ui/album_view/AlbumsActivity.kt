package com.example.mm_photoapp.ui.album_view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mm_photoapp.R
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.ui.photo_view.AlbumPhotosActivity
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_album.*
import javax.inject.Inject

/**
 *  Launcher Activity
 *  Displays a recyclerview list of albums
 */
class AlbumsActivity : DaggerAppCompatActivity(), AlbumAdapter.OnAlbumListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AlbumsViewModel> {viewModelFactory}

    private var recyclerAlbums = mutableListOf<Album>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        setSupportActionBar(toolbar)

        val fetchedAlbums = viewModel.albumList
        viewModel.fetchAlbums()

        fetchedAlbums.observe(this, Observer {

            recyclerAlbums.addAll(it.shuffled())

            recycle_album.also{
                it.layoutManager = LinearLayoutManager(this)

                /** shuffling the AlbumList to have more variety **/
                val albumAdapter = AlbumAdapter(recyclerAlbums, this)
                it.adapter = albumAdapter
            }
        })

    }

    override fun onAlbumClick(pos: Int) {
        val album = recyclerAlbums[pos]

        val intent = Intent(this, AlbumPhotosActivity::class.java).apply {
            putExtra("AlbumID", album.id)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    }

}
