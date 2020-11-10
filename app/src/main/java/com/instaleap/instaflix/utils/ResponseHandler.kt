package com.instaleap.instaflix.utils

import java.net.SocketTimeoutException
import retrofit2.HttpException

open class ResponseHandler {

    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()))
            is SocketTimeoutException -> Resource.error(getErrorMessage(ErrorCodes.SocketTimeOut.code))
            else -> Resource.error(getErrorMessage(Int.MAX_VALUE))
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }

    enum class ErrorCodes(val code: Int) {
        SocketTimeOut(-1)
    }
}
