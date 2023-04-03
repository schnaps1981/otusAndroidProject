package com.imgur.upload.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.imgur.base.recycler.AbstractRecyclerAdapter
import com.imgur.base.recycler.OnItemClickListener
import com.imgur.upload.R
import com.imgur.upload.entity.AccountItemEntity

class AccountImagesItemAdapter(
    onItemClickListener: OnItemClickListener<AccountItemEntity>
) : AbstractRecyclerAdapter<AccountItemEntity, AccountImagesItemHolder>(
    ITEM_COMPARATOR, null, onItemClickListener
) {
    override fun onInitViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = AccountImagesItemHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.account_image_item,
            parent,
            false
        )
    )

    companion object {
        val ITEM_COMPARATOR: DiffUtil.ItemCallback<AccountItemEntity> =
            object : DiffUtil.ItemCallback<AccountItemEntity>() {
                override fun areItemsTheSame(
                    oldItem: AccountItemEntity,
                    newItem: AccountItemEntity
                ): Boolean = oldItem === newItem

                override fun areContentsTheSame(
                    oldItem: AccountItemEntity,
                    newItem: AccountItemEntity
                ): Boolean = oldItem.id == newItem.id
            }
    }
}
