package com.imgur.core_api.tools

interface MainToolsProvider {
    fun provideSnackBarConsumer(): SnackBarConsumer

    fun provideSnackBarProducer(): SnackBarProducer
}
