package com.imgur.base.binding

import android.animation.ValueAnimator
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.imgur.base_ui.R


object GlideBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["loadImageUrl"])
    fun loadImageUrl(imageView: ImageView, url: String) {

        imageView.setImageDrawable(null)

        val tintColor = ContextCompat.getColor(imageView.context, R.color.colorPrimaryWithAlpha20)

        val errorDrawable = ContextCompat.getDrawable(imageView.context, R.drawable.ic_broken_image)
            ?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    colorFilter = BlendModeColorFilter(tintColor, BlendMode.SRC_ATOP)
                } else {
                    setColorFilter(tintColor, PorterDuff.Mode.SRC_ATOP)
                }
            }

        val shimmer = Shimmer
            .ColorHighlightBuilder()
            .setBaseAlpha(1.0f)
            .setHighlightAlpha(0.7f)
            .setBaseColor(ContextCompat.getColor(imageView.context, R.color.white))
            .setHighlightColor(ContextCompat.getColor(imageView.context, R.color.colorPrimary))
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .setDuration(800)
            .setRepeatMode(ValueAnimator.INFINITE)
            .build()

        Glide
            .with(imageView)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(ShimmerDrawable().apply { setShimmer(shimmer) })
            .error(errorDrawable)
            .into(imageView)
    }
}
