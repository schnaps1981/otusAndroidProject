package com.imgur.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.imgur.core_api.AppRootProvider
import com.imgur.search.databinding.FmtSearchBinding
import com.imgur.search.di.SearchComponent
import com.imgur.search.list.SearchItemAdapter
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FmtSearchBinding

    @Inject
    lateinit var adapter: SearchItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootProvider = (requireActivity().application as AppRootProvider).getRootProvider()
        SearchComponent.create(requireContext(), this, rootProvider).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fmt_search, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        with(binding.searchItemsList) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@SearchFragment.adapter
            itemAnimator = null
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.searchResult.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
