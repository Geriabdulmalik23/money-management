package com.geriabdulmalik.moneymanagement.data.service.transactions.response

import com.google.gson.annotations.SerializedName

data class TransactionModel(

    @SerializedName("total_balance")
    val totalBalance: Int?,

    @SerializedName("total_income")
    val totalIncome: Int?,

    @SerializedName("total_expenses")
    val totalExpenses: Int?,

    @SerializedName("transaction_history")
    val transactionHistory: List<TransactionHistoryModel>

)
