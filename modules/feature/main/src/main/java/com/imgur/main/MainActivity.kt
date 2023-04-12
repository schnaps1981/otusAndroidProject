package com.imgur.main

import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.snackbar.Snackbar
import com.imgur.core_api.AppRootProvider
import com.imgur.main.databinding.ActivityMainBinding
import com.imgur.main.di.MainActivityComponent
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    @Inject
    @Named("bottom")
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @Named("overlay")
    lateinit var overlayNavigatorHolder: NavigatorHolder

    private var snackbar: Snackbar? = null

    private val navigator: Navigator =
        AppNavigator(this, R.id.navContainer, supportFragmentManager)

    private val overlayNavigator: Navigator =
        AppNavigator(this, R.id.overlayContainer, supportFragmentManager)

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
        overlayNavigatorHolder.setNavigator(overlayNavigator)

        viewModel.snackBarMessage.observe(this) {
            if (it != 0) {
                showSnackBar(it)
            }
        }
    }

    private fun showSnackBar(@StringRes messageId: Int) {

        val msg = getString(messageId)

        snackbar = Snackbar.make(
            binding.snackBarAnchor,
            msg,
            Snackbar.LENGTH_LONG
        )

        val params = snackbar!!.view.layoutParams as CoordinatorLayout.LayoutParams
        params.anchorId = R.id.snackBarAnchor

        params.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        params.anchorGravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        snackbar!!.view.layoutParams = params

        snackbar!!.show()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        overlayNavigatorHolder.removeNavigator()

        snackbar?.dismiss()

        super.onPause()
    }
}
