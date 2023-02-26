package com.imgur.network_impl.net

import com.imgur.network_api.NetRequest
import javax.inject.Inject

class NetRequestImpl @Inject constructor(): NetRequest {
    override fun performRequest(): String {
        return "Я сделал сетевой запрос"
    }
}
