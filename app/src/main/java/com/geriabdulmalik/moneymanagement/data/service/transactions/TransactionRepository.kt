package com.geriabdulmalik.moneymanagement.data.service.transactions

import android.util.Log
import com.geriabdulmalik.moneymanagement.data.app.AppResponse
import com.geriabdulmalik.moneymanagement.data.app.AppResult
import com.geriabdulmalik.moneymanagement.data.extension.connection
import com.geriabdulmalik.moneymanagement.data.service.transactions.response.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody
import retrofit2.http.Body

class TransactionRepository @Inject constructor(private val mTransactionService: TransactionService) {

    suspend fun getTransactions(): AppResult<TransactionModel> =
        connection {
            val response = mTransactionService.getTransactions()
            if (response.isSuccessful)
                AppResult.OnSuccess(response.body()?.data)
            else
                AppResult.OnFailure(code = response.code())
        }


}