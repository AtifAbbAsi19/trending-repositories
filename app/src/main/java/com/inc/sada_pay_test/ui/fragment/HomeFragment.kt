package com.inc.sada_pay_test.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.FragmentHomeBinding
import com.inc.sada_pay_test.viewmodel.ToolbarViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@AndroidEntryPoint
class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        // Inflate the layout for this fragment
        return binding.root
        // return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init() {
    }


    override fun onDestroyView() {

        if (::binding.isInitialized) {
            binding.unbind()
        }

        super.onDestroyView()
    }


}