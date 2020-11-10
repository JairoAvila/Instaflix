package com.instaleap.instaflix.utils

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    var errorMessage: String? = null
) {

    companion object {
        /**
         * Creates [Resource] object with `SUCCESS` status and [data].
         * Returning object of Resource(Status.SUCCESS, data, null)  last value is null so passing it optionally
         */
        fun <T> success(data: T): Resource<T> =
            Resource(Status.SUCCESS, data)

        /**
         * Creates [Resource] object with `LOADING` status to notify
         * the UI to showing loading.
         * Returning object of Resource(Status.SUCCESS, null, null) last two values are null so passing them optionally
         */
        fun <T> loading(): Resource<T> = Resource(Status.LOADING)

        /**
         * Creates [Resource] object with `ERROR` status and [message].
         * Returning object of Resource(Status.ERROR, errorMessage = message)
         */
        fun <T> error(message: String?): Resource<T> =
            Resource(Status.ERROR, errorMessage = message)
    }
}
