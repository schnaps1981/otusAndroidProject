package com.imgur.di.navigation


import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.imgur.favorites.FavoritesFragment
import com.imgur.search.SearchFragment
import com.imgur.upload.UploadFragment

object Screens {

    fun openSearchScreen() = FragmentScreen { SearchFragment.newInstance() }

    fun openFavoritesScreen() = FragmentScreen { FavoritesFragment.newInstance() }

    fun openUploadScreen() = FragmentScreen { UploadFragment.newInstance() }
}
