package com.example.mm_photoapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Json response converted to a data class
 * @GET "https://jsonplaceholder.typicode.com/photos?albumId={id}"
 */
@Entity(tableName = "photos")
data class Photo(
    val albumId: Int,
    @PrimaryKey
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)