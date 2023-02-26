package com.imgur.network_api

interface NetworkProvider {
    //интерфейс-провайдер сетевого запроса ретрофита.
    //типа imgur.getImages()
    //то, что даст непосредственно вызовы ретрофита
    fun provideNetworkRequest(): NetRequest
}