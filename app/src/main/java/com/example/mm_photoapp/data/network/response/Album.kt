package com.example.mm_photoapp.data.network.response

import androidx.room.Entity

/**
 * Json response converted to a data class
 * @GET "https://jsonplaceholder.typicode.com/albums"
 */
@Entity
data class Album(
    val id: Int,
    val title: String,
    val userId: Int
)