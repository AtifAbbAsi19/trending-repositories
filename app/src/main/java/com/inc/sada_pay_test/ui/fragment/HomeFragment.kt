package com.inc.sada_pay_test.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.adapter.RepositoriesAdapter
import com.inc.sada_pay_test.data.model.reposotryitem.RepositoryItem
import com.inc.sada_pay_test.databinding.FragmentHomeBinding
import com.inc.sada_pay_test.network.networkstates.ApiState
import com.inc.sada_pay_test.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //private val viewModel by viewModels<HomeViewModel>()
    private val sharedViewModel by activityViewModels<HomeViewModel>() //for shared view model


    lateinit var adapter: RepositoriesAdapter

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.setVariable(BR.viewModel, sharedViewModel)
        binding.lifecycleOwner = this


        // Inflate the layout for this fragment
        return binding.root
        // return inflater.inflate(R.layout.fragment_home, container, false)
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = RepositoriesAdapter(arrayListOf()) {


            if (it is RepositoryItem) {
                sharedViewModel.updateItem(it)
            }

            //update item on expend state

        }

        binding.taskRecyclerview.adapter = adapter
        binding.taskRecyclerview.hasFixedSize()

        // viewModel.fetchRepositories()


        lifecycleScope.launch {

            sharedViewModel.uiState.collect {

                when (it) {
                    is ApiState.Success -> {

                        updateUi(it.response)

                    }
                    else -> {
                    }
                }
            }
        }
    }


    private fun updateUi(response: List<RepositoryItem?>?) {

        if (!response.isNullOrEmpty())
            adapter.updateRepositoriesList(repositoryList = response as ArrayList<RepositoryItem>)

    }


    private fun init() {
        // sharedViewModel.fetchRepositories()
    }


    override fun onDestroyView() {

        if (::binding.isInitialized) {
            binding.unbind()
        }

        super.onDestroyView()
    }


}