package com.example.mm_photoapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mm_photoapp.data.db.entities.Album

/**
 *  Communication with the database
 */
@Dao
interface AlbumDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(albumList: List<Album>)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): List<Album>
}