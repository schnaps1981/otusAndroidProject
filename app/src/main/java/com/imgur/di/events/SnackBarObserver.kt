package com.imgur.di.events

import androidx.lifecycle.LiveData
import com.imgur.base.extensions.MutableSafeLiveData
import com.imgur.core_api.tools.SnackBarConsumer
import com.imgur.core_api.tools.SnackBarProducer
import javax.inject.Inject

class SnackBarObserver @Inject constructor() : SnackBarConsumer, SnackBarProducer {
    override val messageStringId: MutableSafeLiveData<Int> = MutableSafeLiveData(0)

    override fun getMessageStringId(): LiveData<Int> = messageStringId
}
