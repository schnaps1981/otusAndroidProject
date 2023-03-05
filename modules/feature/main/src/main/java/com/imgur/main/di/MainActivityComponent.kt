package com.imgur.main.di


import com.imgur.main.MainActivity
import dagger.Component

@Component(
    modules = [MainActivityModule::class]
)
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    companion object {
        fun create(): MainActivityComponent {
            return DaggerMainActivityComponent.builder().build()
        }
    }
}