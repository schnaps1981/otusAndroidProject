package com.imgur.core_api.tools

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import com.imgur.base.extensions.MutableSafeLiveData

interface SnackBarProducer {
    @get:StringRes
    val messageStringId: MutableSafeLiveData<Int>
}

interface SnackBarConsumer {
    @StringRes
    fun getMessageStringId(): LiveData<Int>
}
