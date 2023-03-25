package com.imgur.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.imgur.favorites.databinding.FmtFavoritesBinding
import com.imgur.favorites.di.FavoritesComponent
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FmtFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FavoritesComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fmt_favorites, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    companion object {
        val TAG: String = FavoritesFragment::class.java.simpleName

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment().apply {

            }
        }
    }
}
