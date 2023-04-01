package com.imgur.base_ui.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbstractHolder<out T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView), OnItemClickListenerBinding {

    protected var listener: OnItemClickListener<@UnsafeVariance T>? = null
    protected var model: @UnsafeVariance T? = null

    open fun bind(model: @UnsafeVariance T) {}

    open fun unbind() {}

    fun setData(model: @UnsafeVariance T) {
        this.model = model
        bind(model)
    }

    open fun setOnItemClickListener(listener: OnItemClickListener<@UnsafeVariance T>?) {
        this.listener = listener
    }

    override fun onClickItem(view: View) {
        this.listener?.onItemClick(this.absoluteAdapterPosition, model!!, view.id)
    }

    override fun onLongClickItem(view: View?): Boolean {
        this.listener?.onItemLongClick(this.absoluteAdapterPosition, model!!, view!!.id)
        return true
    }
}
