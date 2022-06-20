package com.inc.sada_pay_test.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.FragmentHomeFailureBinding
import com.inc.sada_pay_test.databinding.FragmentHomeLoadingBinding
import com.inc.sada_pay_test.network.networkstates.ApiState
import com.inc.sada_pay_test.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeLoadingFragment : Fragment() {


    lateinit var binding: FragmentHomeLoadingBinding

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        if (::binding.isInitialized.not()) {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home_loading,
                container,
                false
            )
        }

        return binding.root
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewModel.fetchRepositories()

        lifecycleScope.launch {

            viewModel.uiState.collect {

                when (it) {
                    is ApiState.Success -> {
                        navigateToSuccessFragment()
                    }
                    is ApiState.Failure -> {
                        navigateToFailureFragment()
                    }
                    else -> {
                    }
                }
            }
        }
    }


    private fun navigateToFailureFragment() {

        findNavController().apply {
            navigate(R.id.failureFragment)
            backQueue.clear()
        }

        // findNavController().navigate(R.id.failureFragment)
    }

    private fun navigateToSuccessFragment() {

        findNavController().apply {
            navigate(R.id.homeFragment)
            backQueue.clear()
        }


        //findNavController().navigate(R.id.homeFragment)
        // findNavController().popBackStack(R.id.homeFragment, true)
    }


    override fun onDestroyView() {

        if (::binding.isInitialized) {
            binding.unbind()
        }

        super.onDestroyView()
    }

}