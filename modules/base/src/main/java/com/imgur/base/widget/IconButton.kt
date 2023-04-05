package com.imgur.base.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.imgur.base_ui.R
import com.imgur.base_ui.databinding.WidgetIconButtonBinding
import java.lang.ref.WeakReference

interface OnWidgetClickListener {
    fun onClick(v: View)
}

@BindingMethods(
    BindingMethod(
        type = IconButton::class,
        attribute = "clickListener",
        method = "setClickListener"
    )
)

class IconButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defaultStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defaultStyleRes) {

    private var listener: WeakReference<OnWidgetClickListener>? = null

    private val colorTransparent = ContextCompat.getColor(context, android.R.color.transparent)

    private val inflater = LayoutInflater.from(context)
    private var binding = WidgetIconButtonBinding.inflate(inflater, this, true)

    private var icon: Drawable? = null

    @ColorInt
    private var tintColor: Int = colorTransparent

    init {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IconButton)
        try {
            icon = typedArray.getDrawable(R.styleable.IconButton_icon)

            tintColor = typedArray.getColor(R.styleable.IconButton_tintColor, colorTransparent)
        } finally {
            typedArray.recycle()
        }

        binding.container.setOnClickListener {
            listener?.get()?.onClick(it)
        }

        icon?.let { binding.icon.setImageDrawable(it) }

        if (tintColor != colorTransparent) {
            binding.icon.setColorFilter(tintColor)
        }
    }

    override fun onDetachedFromWindow() {
        this.listener = null

        super.onDetachedFromWindow()
    }

    fun setClickListener(listener: OnWidgetClickListener?) {
        this.listener = WeakReference(listener)
    }
}
