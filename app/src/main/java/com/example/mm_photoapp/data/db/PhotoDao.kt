package com.example.mm_photoapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mm_photoapp.data.db.entities.Photo

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(albumList: List<Photo>)

    @Query("SELECT * FROM photos")
    fun getAllPhotos(): List<Photo>

    @Query("SELECT * FROM photos WHERE albumId = :id")
    fun getAlbumPhotos(id: Int): List<Photo>
}