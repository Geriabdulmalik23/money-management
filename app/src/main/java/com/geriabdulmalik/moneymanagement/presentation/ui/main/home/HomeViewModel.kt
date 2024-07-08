package com.geriabdulmalik.moneymanagement.presentation.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geriabdulmalik.moneymanagement.data.extension.addOnResultListener
import com.geriabdulmalik.moneymanagement.data.service.transactions.TransactionRepository
import com.geriabdulmalik.moneymanagement.data.service.transactions.response.TransactionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val mTransactionRepository: TransactionRepository) :
    ViewModel() {

    private val _pageLoading by lazy { MutableLiveData<Boolean>() }
    val pageLoading: LiveData<Boolean>
        get() = _pageLoading

    private val _transactions by lazy { MutableLiveData<TransactionModel>() }
    fun getTransactions(): LiveData<TransactionModel> {
        viewModelScope.launch {
            _pageLoading.postValue(true)
            mTransactionRepository.getTransactions().addOnResultListener(
                onSuccess = { data ->
                    _pageLoading.postValue(false)
                    _transactions.postValue(data)
                },
                onFailure = { _, _ ->
                    _pageLoading.postValue(false)
                },
                onError = {
                    _pageLoading.postValue(false)
                }
            )
        }
        return _transactions
    }

}