package com.imgur.core_impl.datastore

import javax.crypto.SecretKey

interface ISecretKey {
    fun getSecretKey(): SecretKey

    companion object {
        const val ANDROID_KEY_STORE = "AndroidKeyStore"
    }
}
