package com.geriabdulmalik.moneymanagement.presentation.ui.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.geriabdulmalik.moneymanagement.databinding.FragmentHomeBinding
import com.geriabdulmalik.moneymanagement.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.geriabdulmalik.moneymanagement.R.*
import com.geriabdulmalik.moneymanagement.data.app.AppObserver
import com.geriabdulmalik.moneymanagement.extension.asDigit
import com.geriabdulmalik.moneymanagement.presentation.ui.main.home.adapter.TransactionHistoryAdapter


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), AppObserver {

    private val mViewModel: HomeViewModel by viewModels()
    private lateinit var mAdapterHistory: TransactionHistoryAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        views.apply {
            srlMain.setOnRefreshListener {
                mViewModel.getTransactions()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewModelObserver() {
        mViewModel.getTransactions().observe(viewLifecycleOwner) {
            Toast.makeText(context, "Halo", Toast.LENGTH_SHORT).show()
            views.apply {

                tvTotalBalance.text = it.totalBalance?.asDigit(prefix = "RP")
                tvIncome.text = it.totalIncome?.asDigit(prefix = "RP")
                tvExpense.text = it.totalExpenses?.asDigit(prefix = "RP")

                mAdapterHistory = TransactionHistoryAdapter(transactionList = it.transactionHistory)

                rvTransactions.apply {
                    adapter = mAdapterHistory
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
            }
        }
        mViewModel.pageLoading.observe(viewLifecycleOwner) {

            views.apply {
                srlMain.isRefreshing = it
                if (it) {
                    tvTotalBalance.visibility = View.INVISIBLE
                    tvIncome.visibility = View.INVISIBLE
                    tvExpense.visibility = View.INVISIBLE
                    sflTotalBalance.visibility = View.VISIBLE
                    sflTotalExpense.visibility = View.VISIBLE
                    sflTotalIncome.visibility = View.VISIBLE

                    sflTotalBalance.startShimmer()
                    sflTotalExpense.startShimmer()
                    sflTotalIncome.startShimmer()

                } else {

                    tvTotalBalance.visibility = View.VISIBLE
                    tvIncome.visibility = View.VISIBLE
                    tvExpense.visibility = View.VISIBLE
                    sflTotalBalance.visibility = View.INVISIBLE
                    sflTotalExpense.visibility = View.INVISIBLE
                    sflTotalIncome.visibility = View.INVISIBLE

                    sflTotalBalance.stopShimmer()
                    sflTotalExpense.stopShimmer()
                    sflTotalIncome.stopShimmer()

                }
            }
        }
    }
}


