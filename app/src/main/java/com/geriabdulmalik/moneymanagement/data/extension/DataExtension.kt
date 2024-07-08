package com.geriabdulmalik.moneymanagement.data.extension

import com.geriabdulmalik.moneymanagement.data.app.AppResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> AppResult<T>.addOnResultListener(
    onSuccess: (data: T?) -> Unit,
    onFailure: (data: T?, code: Int?) -> Unit,
    onError: (throwable: Throwable?) -> Unit
) {

    when (this) {
        is AppResult.OnSuccess -> onSuccess(this.data)
        //should pass message here, but hard to refactor all code
        is AppResult.OnFailure -> onFailure(this.data, this.code)
        is AppResult.OnError -> onError(this.throwable)
    }

}

inline fun <T> connection(run: () -> AppResult<T>): AppResult<T> =
    try {
        run()
    } catch (e: Throwable) {
        AppResult.OnError(e)
    }

inline fun <reified T> errorBody(json: String?): T =
    Gson().fromJson(json, object : TypeToken<T>() {}.type)

@Suppress("UNCHECKED_CAST")
fun <T> Map<String, T?>.removeNulls(): Map<String, T> {
    return filterValues { it != null } as Map<String, T>
}

inline fun <reified T> response(json: String?): T =
    Gson().fromJson(json, object : TypeToken<T>() {}.type)