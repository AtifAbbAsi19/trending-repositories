package com.inc.sada_pay_test.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.FragmentHomeBinding
import com.inc.sada_pay_test.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    //private val sharedViewModel by activityViewModels<HomeViewModel>() //for shared view model

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
       // binding.setVariable(BR.viewModel,viewModel)


        // Inflate the layout for this fragment
        return binding.root
        // return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewModel.fetchRepositories()
    }


    override fun onDestroyView() {

        if (::binding.isInitialized) {
            binding.unbind()
        }

        super.onDestroyView()
    }


}