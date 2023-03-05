package com.imgur.upload.di

import com.imgur.upload.UploadFragment
import dagger.Component

@Component(
    modules = [UploadFragmentModule::class]
)
interface UploadComponent {

    fun inject(fragment: UploadFragment)

    companion object {
        fun create(): UploadComponent {
            return DaggerUploadComponent.builder().build()
        }
    }
}