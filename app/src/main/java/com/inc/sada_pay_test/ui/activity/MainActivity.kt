package com.inc.sada_pay_test.ui.activity



import android.R.menu
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.inc.sada_pay_test.R
import com.inc.sada_pay_test.databinding.ActivityMainBinding
import com.inc.sada_pay_test.viewmodel.HomeViewModel
import com.inc.sada_pay_test.viewmodel.ToolbarViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var toolbarViewModel: ToolbarViewModel

    lateinit var binding: ActivityMainBinding

    private val viewModel: HomeViewModel by viewModels() //for shared view model

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.toolbarViewModel = toolbarViewModel
        binding.lifecycleOwner = this
        setupToolBar()
        init()
    }

    private fun setupToolBar() {

        setSupportActionBar(binding.appBarLayout.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = ""
    }


    private fun init() {
        toolbarViewModel.title.postValue(getString(R.string.trending))

        binding.appBarLayout.settingBtnToolbar.setOnClickListener {
            showPopupMenu(it)
        }

        //setupNavigationGraph()
    }

    private fun setupNavigationGraph() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.home_nav)
        val navController = navHostFragment.navController
        val destination = R.id.loadingFragment
        navGraph.setStartDestination(destination)
        navController.graph = navGraph

    }

    private fun showPopupMenu(view: View) {
        PopupMenu(view.context, view).apply {
            menuInflater.inflate(R.menu.menu_main, menu)
            setOnMenuItemClickListener { item ->
                onOptionsItemSelected(item)
                true
            }
        }.show()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

         menuInflater.inflate(R.menu.menu_main, menu)

        lifecycleScope.launch {

            val isDarkModeEnabled = toolbarViewModel.getDarkModeSetting().first()

            menu?.findItem(R.id.dark_theme_btn)?.isChecked = isDarkModeEnabled

            toolbarViewModel.isDarkModeEnabled.value = isDarkModeEnabled
        }

        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {

            R.id.refresh_btn -> {
                viewModel.fetchRepositories()
                true
            }

            R.id.dark_theme_btn -> {
                item.isChecked = !item.isChecked
                setupThemeMode(item.isChecked)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun setupThemeMode(checked: Boolean) {


        lifecycleScope.launch {
            toolbarViewModel.saveDarkModeSetting(checked)
        }

        val mode = if (checked) {
            AppCompatDelegate
                .MODE_NIGHT_YES
        } else {
            AppCompatDelegate
                .MODE_NIGHT_NO
        }

        AppCompatDelegate
            .setDefaultNightMode(
                mode
            )

    }

    override fun onBackPressed() {
        finish()
    }


    override fun onDestroy() {
        if (::binding.isInitialized) {
            binding.unbind()
        }
        super.onDestroy()
    }
}