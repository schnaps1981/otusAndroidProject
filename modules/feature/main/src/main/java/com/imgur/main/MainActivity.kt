package com.imgur.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.imgur.core_api.AppRootProvider
import com.imgur.main.databinding.ActivityMainBinding
import com.imgur.main.di.MainActivityComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator =
        AppNavigator(this, R.id.navContainer, this.supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainActivityComponent.create((application as AppRootProvider).getRootProvider())
            .inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.bottomNavHandler = viewModel
        binding.viewModel = viewModel
        lifecycle.addObserver(viewModel)
    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()

        super.onPause()
    }
}
