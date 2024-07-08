package com.geriabdulmalik.moneymanagement.data.app

sealed class AppResult<T> {

    data class OnSuccess<T>(val data: T?, val code: Int? = null) : AppResult<T>()

    data class OnFailure<T>(
        val data: T? = null,
        val code: Int? = null,
        val message: String? = null
    ) : AppResult<T>()

    data class OnError<T>(val throwable: Throwable?) : AppResult<T>()

}