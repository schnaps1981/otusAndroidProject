package com.imgur.base_ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object GlideBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["loadImageUrl"])
    fun loadImageUrl(imageView: ImageView, url: String) {

        imageView.setImageDrawable(null)

        Glide
            .with(imageView)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(imageView)
    }
}
