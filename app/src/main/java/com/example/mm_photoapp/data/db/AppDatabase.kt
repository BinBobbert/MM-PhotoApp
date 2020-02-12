package com.example.mm_photoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.data.db.entities.Photo

@Database(
    entities = [Album::class, Photo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun photoAlbumDao(): PhotoAlbumDao

}