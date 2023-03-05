package com.imgur.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.imgur.favorites.databinding.FmtFavoritesBinding
import com.imgur.favorites.di.FavoritesComponent
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var binding: FmtFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FavoritesComponent.create().inject(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    companion object {
        val TAG: String = FavoritesFragment::class.java.simpleName

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment().apply {

            }
        }
    }
}