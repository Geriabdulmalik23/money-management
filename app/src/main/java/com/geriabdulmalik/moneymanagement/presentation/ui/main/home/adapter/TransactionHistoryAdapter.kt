package com.geriabdulmalik.moneymanagement.presentation.ui.main.home.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geriabdulmalik.moneymanagement.R
import com.geriabdulmalik.moneymanagement.data.service.transactions.response.TransactionHistoryModel
import com.geriabdulmalik.moneymanagement.databinding.ItemListTransactionHistoryBinding
import com.geriabdulmalik.moneymanagement.extension.asDate
import com.geriabdulmalik.moneymanagement.extension.asDigit

class TransactionHistoryAdapter(private val transactionList: List<TransactionHistoryModel>) :
    RecyclerView.Adapter<TransactionHistoryAdapter.PaymentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val itemBinding = ItemListTransactionHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaymentHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val listItem: TransactionHistoryModel = transactionList[position]
        holder.bind(listItem)
    }

    override fun getItemCount(): Int = transactionList.size

    class PaymentHolder(private val itemBinding: ItemListTransactionHistoryBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: TransactionHistoryModel) {
            itemBinding.tvNameTransaction.text = item.description
            itemBinding.tvDate.text = item.transactionDate
            itemBinding.tvType.text =
                item.amount?.asDigit(prefix = if (item.transactionType == "income") "+ RP" else "- RP")
            itemBinding.tvType.setTextColor(
                if (item.transactionType == "income") Color.parseColor("#25A969") else Color.RED
            )
            itemBinding.ivCategory.setImageResource(if (item.transactionType == "income") R.drawable.ic_arrow_down_rounded else R.drawable.ic_arrow_top_rounded)
        }
    }

}