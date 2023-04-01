package com.imgur.search.list

import com.imgur.base_ui.recycler.AbstractHolder
import com.imgur.search.databinding.SearchResultItemBinding
import com.imgur.search.entity.SearchItemEntity

class SearchItemHolder(
    private val binding: SearchResultItemBinding
) : AbstractHolder<SearchItemEntity>(binding.root) {

    override fun bind(model: SearchItemEntity) {
        super.bind(model)

        binding.model = model
        binding.handler = this

        binding.executePendingBindings()
    }
}
