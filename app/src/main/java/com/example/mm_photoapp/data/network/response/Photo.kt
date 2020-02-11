package com.example.mm_photoapp.data.network.response

/**
 * Json response converted to a data class
 * @GET "https://jsonplaceholder.typicode.com/photos?albumId={id}"
 */
data class Photo(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)