package com.inc.sada_pay_test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.RepositioryItemViewBinding
import com.inc.sada_pay_test.data.model.reposotryitem.RepositoryItem

class RepositoriesAdapter(repositoryList: ArrayList<RepositoryItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var repositoryList: ArrayList<RepositoryItem> = arrayListOf()


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
        (holder as RepositoriesViewHolder).bind(repositoryList[position])
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    inner class RepositoriesViewHolder(val binding: RepositioryItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repositoryItem: RepositoryItem) {

            //binding.setVariable(repositoryItem)
            //binding.executePendingBindings()


        }


    }

}