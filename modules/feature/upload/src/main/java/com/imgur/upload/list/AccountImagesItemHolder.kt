package com.imgur.upload.list

import com.imgur.base.recycler.AbstractHolder
import com.imgur.upload.databinding.AccountImageItemBinding
import com.imgur.upload.entity.AccountItemEntity

class AccountImagesItemHolder(
    private val binding: AccountImageItemBinding
) : AbstractHolder<AccountItemEntity>(binding.root) {

    override fun bind(model: AccountItemEntity) {
        super.bind(model)

        binding.model = model
        binding.handler = this

        binding.executePendingBindings()
    }
}
