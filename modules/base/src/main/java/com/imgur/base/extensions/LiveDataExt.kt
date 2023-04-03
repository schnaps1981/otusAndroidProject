package com.imgur.base.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> LiveData<T>.debounce(
    duration: Long = 1000L,
    coroutineScope: CoroutineScope
): MediatorLiveData<T> {
    return MediatorLiveData<T>().also { data ->

        val source = this
        var job: Job? = null

        data.addSource(source) {
            job?.cancel()
            job = coroutineScope.launch {
                delay(duration)
                data.value = source.value
            }
        }
    }
}
