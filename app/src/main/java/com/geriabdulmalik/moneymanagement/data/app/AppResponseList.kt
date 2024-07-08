package com.geriabdulmalik.moneymanagement.data.app

import com.google.gson.annotations.SerializedName

data class AppResponseList<T>(
    @field:SerializedName("code") val code: String? = null,
    @field:SerializedName("data") val data: List<T>? = null
)