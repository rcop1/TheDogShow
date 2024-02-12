package com.rodrigocopetti.thedogshow.data.network

/**
 * Catalogues all possible states when carrying out network operations
 */
enum class OperationStatus {
    STOPPED,
    IN_PROGRESS
}

/**
 * Data structure that stores the information about a network operation
 *
 * @see [OperationStatus]
 */
data class NetworkOperation(
    val error: String? = null,
    val status: OperationStatus = OperationStatus.STOPPED
) {
    constructor(exception: Throwable) : this(
        exception.localizedMessage,
        OperationStatus.STOPPED
    )
}
