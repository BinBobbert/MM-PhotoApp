package com.example.mm_photoapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mm_photoapp.data.db.entities.Album
import com.example.mm_photoapp.data.db.entities.Photo

/**
 *  Communication with the database
 */
@Dao
interface PhotoAlbumDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAlbums(albumList: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertPhotos(albumList: List<Photo>)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): List<Album>

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun getAlbumPhotos(albumId: Int):  List<Photo>
}