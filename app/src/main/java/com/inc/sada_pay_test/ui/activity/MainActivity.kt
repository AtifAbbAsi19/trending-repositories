package com.inc.sada_pay_test.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.ActivityMainBinding
import com.inc.sada_pay_test.viewmodel.ToolbarViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbarViewModel = toolbarViewModel
        binding.lifecycleOwner = this
        init()


    }

    fun init() {

        toolbarViewModel.title.postValue(getString(R.string.trending))
    }


    override fun onDestroy() {

        if (::binding.isInitialized) {
            binding.unbind()
        }

        super.onDestroy()


    }

}