package com.imgur.network_api

import com.imgur.network_api.api.ImgurApi

interface NetworkProvider {
    fun provideImgurApi(): ImgurApi
}
