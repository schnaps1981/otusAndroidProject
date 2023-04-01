package com.imgur.base_ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object GlideBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["loadImageUrl"])
    fun loadImageUrl(imageView: ImageView, url: String) {

        imageView.setImageDrawable(null)

        Glide.with(imageView).load(url).into(imageView)
    }
}
