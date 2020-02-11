package com.example.mm_photoapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Json response converted to a data class
 * @GET "https://jsonplaceholder.typicode.com/albums"
 */
@Entity(tableName = "albums")
data class Album(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
)