package com.example.mm_photoapp.ui.album_view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mm_photoapp.R
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_album.*
import javax.inject.Inject

/**
 *  Launcher Activity
 *  Displays a recyclerview list of albums
 */
class AlbumsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AlbumsViewModel> {viewModelFactory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        setSupportActionBar(toolbar)

        val fetchedAlbums = viewModel.albumList
        viewModel.fetchAlbums()

        fetchedAlbums.observe(this, Observer {

            recycle_album.also{
                it.layoutManager = LinearLayoutManager(this)

                /** shuffling the AlbumList to have more variety **/
                val albumAdapter = AlbumAdapter(fetchedAlbums.value!!.shuffled())
                it.adapter = albumAdapter
            }
        })

    }

}
