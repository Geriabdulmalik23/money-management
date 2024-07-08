package com.geriabdulmalik.moneymanagement.data.app

import com.google.gson.annotations.SerializedName

data class AppResponse<T>(
    @SerializedName("code") val code: String? = null,
    @SerializedName("data") val data: T? = null
)