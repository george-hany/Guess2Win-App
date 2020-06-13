package com.core.network.model

import retrofit2.Response

sealed class Result<out T : Any> {
    data class Success<T : Any>(val data: Response<T>) : Result<T>()
    data class Error<T : Any>(val data: Response<T>) : Result<T>()
    data class SuccessMocked<out T : Any>(val data: T) : Result<T>()
}
