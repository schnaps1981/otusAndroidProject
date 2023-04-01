package com.imgur.search.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.imgur.base_ui.recycler.AbstractRecyclerAdapter
import com.imgur.base_ui.recycler.OnItemClickListener
import com.imgur.base_ui.recycler.PagedInteraction
import com.imgur.search.R
import com.imgur.search.entity.SearchItemEntity

class SearchItemAdapter(
    onItemClickListener: OnItemClickListener<SearchItemEntity>,
    pagedInteraction: PagedInteraction
) : AbstractRecyclerAdapter<SearchItemEntity, SearchItemHolder>(
    ITEM_COMPARATOR, pagedInteraction, onItemClickListener
) {
    override fun onInitViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = SearchItemHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.search_result_item,
            parent,
            false
        )
    )

    companion object {
        val ITEM_COMPARATOR: DiffUtil.ItemCallback<SearchItemEntity> =
            object : DiffUtil.ItemCallback<SearchItemEntity>() {
                override fun areItemsTheSame(
                    oldItem: SearchItemEntity,
                    newItem: SearchItemEntity
                ): Boolean = oldItem === newItem

                override fun areContentsTheSame(
                    oldItem: SearchItemEntity,
                    newItem: SearchItemEntity
                ): Boolean = oldItem.id == newItem.id
            }
    }
}
