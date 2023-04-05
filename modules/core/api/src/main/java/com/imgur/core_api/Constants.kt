package com.imgur.core_api

object Constants {
    const val imgurAppId = "8b08c59efdc2223"
    const val imgurAppSecret = "57ca5ac9eb7ca733f89a30864cab5dc800121285"
    const val imgurAppRedirect = "https://imgur.com/"
    const val imgurAuthUrl =
        "https://api.imgur.com/oauth2/authorize?client_id=${imgurAppId}&response_type=token"

    const val endPoint: String = "https://api.imgur.com/"

    const val okHttpConnectionTimeOut: Long = 10L
}
