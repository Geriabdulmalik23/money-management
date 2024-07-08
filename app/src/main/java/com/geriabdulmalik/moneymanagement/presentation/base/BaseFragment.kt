package com.geriabdulmalik.moneymanagement.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.geriabdulmalik.moneymanagement.data.app.AppObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseFragment<B : ViewBinding> : DialogFragment() {

    protected lateinit var views: B

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        views = bindingInflater.invoke(inflater, container, false)

        if (this is AppObserver) onViewModelObserver()

        return views.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}