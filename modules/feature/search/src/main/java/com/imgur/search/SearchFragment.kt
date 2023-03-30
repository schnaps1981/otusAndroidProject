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
import com.imgur.search.databinding.FmtSearchBinding
import com.imgur.search.di.SearchComponent
import com.imgur.search.list.SearchItemAdapter
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FmtSearchBinding

    private val viewModel: SearchViewModel by viewModels { viewModelFactory }

    private val adapter by lazy { SearchItemAdapter(viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SearchComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fmt_search, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //adapter.setHasStableIds(true)

        with(binding.searchItemsList) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@SearchFragment.adapter
            itemAnimator = null
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Timber.d("$it")
        }
    }

    companion object {
        val TAG: String = SearchFragment::class.java.simpleName

        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }
}
