package com.inc.sada_pay_test.adapter

import android.annotation.SuppressLint
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.data.model.reposotryitem.RepositoryItem
import com.inc.sada_pay_test.databinding.RepositioryItemViewBinding


class RepositoriesAdapter(repositoryList: ArrayList<RepositoryItem>, val onClick: (Any) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var repositoryList: ArrayList<RepositoryItem> = arrayListOf()

    init {
        this.repositoryList = repositoryList
    }


    fun updateRepositoriesList(repositoryList: ArrayList<RepositoryItem>) {
        this.repositoryList = repositoryList
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val binding: RepositioryItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.repositiory_item_view, parent, false
        )

        return RepositoriesViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (repositoryList.isNotEmpty())
            (holder as RepositoriesViewHolder).bind(repositoryList[position])
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    inner class RepositoriesViewHolder(val binding: RepositioryItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repositoryItem: RepositoryItem) {

            binding.item = repositoryItem
            binding.executePendingBindings()

            binding.cardLayout.setOnClickListener {
                onClick.invoke(repositoryItem)
            }

        }


    }

}