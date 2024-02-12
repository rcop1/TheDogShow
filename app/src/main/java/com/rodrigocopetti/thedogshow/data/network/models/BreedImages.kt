package com.rodrigocopetti.thedogshow.data.network.models

/**
 * Model used for retrieving a list of breed images from the API
 */
data class BreedImagesResponse(
    val message: List<String>,
    val status: String
)

/**
 * Wrapper to better integrate with Android's APIs
 */
data class BreedImages(
    val images: List<String>,
    val breed: String
)