package com.inc.sada_pay_test.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.FragmentHomeFailureBinding
import com.inc.sada_pay_test.network.networkstates.ApiState
import com.inc.sada_pay_test.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFailureFragment : Fragment() {

    lateinit var binding: FragmentHomeFailureBinding

    private val viewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home_failure,
            container,
            false
        )

        return binding.root

    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.retryButton.setOnClickListener {
            fetchRepositories()
        }




        lifecycleScope.launch {

            viewModel.uiState.collect {

                when (it) {
                    is ApiState.Loading -> {
                        navigateToLoadingScreen()
                    }

                    else -> {
                    }
                }
            }
        }
    }

    private fun navigateToLoadingScreen() {

        findNavController().apply {
            navigate(R.id.loadingFragment)
            backQueue.clear()
        }
    }

    private fun navigateToSuccessFragment() {
        findNavController().navigate(R.id.homeFragment)
        // findNavController().popBackStack(R.id.homeFragment, true)
    }


    private fun fetchRepositories() {
        viewModel.fetchRepositories()
    }


    override fun onDestroyView() {

        if (::binding.isInitialized) {
            binding.unbind()
        }

        super.onDestroyView()
    }


}