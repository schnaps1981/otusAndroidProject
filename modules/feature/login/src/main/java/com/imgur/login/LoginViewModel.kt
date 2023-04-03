package com.imgur.login

import androidx.lifecycle.ViewModel
import com.imgur.core_api.navigation.OverlayNavRouter
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val router: OverlayNavRouter
) : ViewModel() {

    init {
        println()
    }

    fun onBackClick() {
        router.back()
    }
}
