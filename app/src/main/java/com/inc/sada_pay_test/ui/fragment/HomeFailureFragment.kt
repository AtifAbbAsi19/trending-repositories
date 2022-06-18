package com.inc.sada_pay_test.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.FragmentHomeFailureBinding


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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.retryButton.setOnClickListener {
            fetchRepositories()
        }


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