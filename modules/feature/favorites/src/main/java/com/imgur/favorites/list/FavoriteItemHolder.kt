package com.imgur.favorites.list

import com.imgur.base_ui.recycler.AbstractHolder
import com.imgur.favorites.databinding.FavoriteItemBinding
import com.imgur.favorites.entity.FavoriteEntity

class FavoriteItemHolder(
    private val binding: FavoriteItemBinding
) : AbstractHolder<FavoriteEntity>(binding.root) {

    override fun bind(model: FavoriteEntity) {
        super.bind(model)

        binding.model = model
        binding.handler = this

        binding.executePendingBindings()
    }
}
