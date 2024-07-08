package com.geriabdulmalik.moneymanagement.data.service.transactions

import com.geriabdulmalik.moneymanagement.data.app.AppResponse
import com.geriabdulmalik.moneymanagement.data.service.transactions.response.TransactionModel
import retrofit2.Response
import retrofit2.http.GET

interface TransactionService {

    @GET("transactions")
    suspend fun getTransactions(): Response<AppResponse<TransactionModel>>

}