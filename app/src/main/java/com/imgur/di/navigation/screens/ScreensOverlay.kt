package com.imgur.di.navigation.screens


import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.imgur.login.LoginFragment

object ScreensOverlay {

    fun openLoginScreen() = FragmentScreen { LoginFragment.newInstance() }
}
