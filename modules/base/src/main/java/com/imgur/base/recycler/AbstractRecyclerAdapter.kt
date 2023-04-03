package com.imgur.base.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

interface PagedInteraction {
    val prefetchDistance: Int

    fun onFetchNext(): Boolean
}

abstract class AbstractRecyclerAdapter<D : Any, T : AbstractHolder<D>>(
    private val diffCallback: DiffUtil.ItemCallback<D>,
    private val pagedInteraction: PagedInteraction? = null,
    protected var onItemClickListener: OnItemClickListener<D>? = null
) : RecyclerView.Adapter<T>() {

    private var differ = Differ(this, diffCallback, emptyList(), emptyList())

    private var loadingState: PageableLoadedState = PageableLoadedState.Loaded

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val inflater = LayoutInflater.from(parent.context)

        return onInitViewHolder(parent, inflater, viewType)
    }

    protected abstract fun onInitViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): T

    override fun onBindViewHolder(holder: T, position: Int) {
        holder.setData(differ.getItem(position))
        holder.setOnItemClickListener(onItemClickListener)
    }

    override fun getItemViewType(position: Int): Int {
        if (pagedInteraction != null && loadingState == PageableLoadedState.Loaded &&
            differ.size > pagedInteraction.prefetchDistance &&
            differ.size - position < pagedInteraction.prefetchDistance
        ) {
            loadingState = if (pagedInteraction.onFetchNext())
                PageableLoadedState.Loading else PageableLoadedState.Loaded
        }

        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return differ.size
    }

    fun getItems(): List<D> = differ.newList

    open fun submitList(list: List<D>) {
        differ = Differ(this, diffCallback, list, differ.newList)
        differ.calculateDiff()
        loadingState = PageableLoadedState.Loaded
    }

    override fun onViewRecycled(holder: T) {
        holder.unbind()

        super.onViewRecycled(holder)
    }

    fun setOnClickListener(listener: OnItemClickListener<D>?) {
        onItemClickListener = listener
    }
}
