package com.geriabdulmalik.moneymanagement.data.service.transactions.response

import com.google.gson.annotations.SerializedName

data class TransactionHistoryModel(

    @SerializedName("description")
    val description: String?,

    @SerializedName("transaction_type")
    val transactionType: String?,

    @SerializedName("transaction_date")
    val transactionDate: String?,

    @SerializedName("amount")
    val amount: Int?

)
