package com.imgur.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.imgur.search.databinding.FmtSearchBinding
import com.imgur.search.di.SearchComponent
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FmtSearchBinding

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SearchComponent.create().inject(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName

        fun newInstance(): SearchFragment {
            return SearchFragment().apply {

            }
        }
    }
}