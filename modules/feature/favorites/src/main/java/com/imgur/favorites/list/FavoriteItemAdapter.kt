package com.imgur.favorites.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.imgur.base.recycler.AbstractRecyclerAdapter
import com.imgur.base.recycler.OnItemClickListener
import com.imgur.core_api.viewmodel.TestOpen
import com.imgur.favorites.R
import com.imgur.favorites.entity.FavoriteEntity

@TestOpen
class FavoriteItemAdapter(
    onItemClickListener: OnItemClickListener<FavoriteEntity>
) : AbstractRecyclerAdapter<FavoriteEntity, FavoriteItemHolder>(
    ITEM_COMPARATOR, null, onItemClickListener
) {
    override fun onInitViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = FavoriteItemHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.favorite_item,
            parent,
            false
        )
    )

    companion object {
        val ITEM_COMPARATOR: DiffUtil.ItemCallback<FavoriteEntity> =
            object : DiffUtil.ItemCallback<FavoriteEntity>() {
                override fun areItemsTheSame(
                    oldItem: FavoriteEntity,
                    newItem: FavoriteEntity
                ): Boolean = oldItem === newItem

                override fun areContentsTheSame(
                    oldItem: FavoriteEntity,
                    newItem: FavoriteEntity
                ): Boolean = oldItem.id == newItem.id
            }
    }
}
