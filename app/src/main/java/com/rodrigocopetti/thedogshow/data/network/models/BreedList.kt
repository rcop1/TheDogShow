package com.rodrigocopetti.thedogshow.data.network.models

import java.util.Dictionary

/**
 * Model used for retrieving a list of breeds from the Dogs API
 *
 * Example JSON:
 * {
 *     "message": {
 *         "affenpinscher": [],
 *         "african": [],
 *         "airedale": [],
 *         "akita": [],
 *         "appenzeller": [],
 *         "australian": [
 *             "shepherd"
 *         ]
 *     },
 *     status: "success"
 * }
 */
data class BreedListResponse(
    val message: BreedDictionary,
    val status: String
)
typealias BreedDictionary = Map<String, List<String>>

/**
 * Helper functions to better integrate with Android's APIs
 */
fun BreedDictionary.toList(): List<String> = this.keys.toList()