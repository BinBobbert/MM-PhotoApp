package com.example.mm_photoapp.di

import androidx.lifecycle.ViewModel
import com.example.mm_photoapp.ui.album_view.AlbumsActivity
import com.example.mm_photoapp.ui.album_view.AlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * This class specifies that AlbumActivity and its dependencies are candidates for data injection
 * The activity gets injected with a factory and a viewModel specification
 */
@Module
abstract class AlbumModule{

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun albumActivity(): AlbumsActivity

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindViewModel(viewModel: AlbumsViewModel): ViewModel
}