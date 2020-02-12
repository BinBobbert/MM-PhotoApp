package com.example.mm_photoapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.mm_photoapp.di.ViewModelBuilder
import com.example.mm_photoapp.di.ViewModelKey
import com.example.mm_photoapp.ui.photo_view.AlbumPhotosActivity
import com.example.mm_photoapp.ui.photo_view.AlbumPhotosViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * This class specifies that AlbumPhotosActivity and its dependencies are candidates for data injection
 * The activity gets injected with a factory and a viewModel specification
 */
@Module
abstract class AlbumPhotosModule{

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    internal abstract fun albumPhotosActivity(): AlbumPhotosActivity

    @Binds
    @IntoMap
    @ViewModelKey(AlbumPhotosViewModel::class)
    abstract fun bindViewModel(viewModel: AlbumPhotosViewModel): ViewModel
}