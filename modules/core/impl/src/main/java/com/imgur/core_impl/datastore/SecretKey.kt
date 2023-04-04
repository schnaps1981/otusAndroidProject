package com.imgur.core_impl.datastore

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class SecretKey : ISecretKey {

    private val keyStore by lazy {
        KeyStore.getInstance(ISecretKey.ANDROID_KEY_STORE).apply {
            load(null)
        }
    }

    override fun getSecretKey(): SecretKey {
        return keyStore.getKey(AES_KEY_ALIAS, null) as? SecretKey ?: generateAesSecretKey()
    }

    private fun generateAesSecretKey() =
        KeyGenerator.getInstance(AES_ALGORITHM, ISecretKey.ANDROID_KEY_STORE).apply {
            init(getKeyGenSpec())
        }.generateKey()

    private fun getKeyGenSpec(): KeyGenParameterSpec =
        KeyGenParameterSpec.Builder(
            AES_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setRandomizedEncryptionRequired(false)
            .build()

    companion object {
        private const val AES_KEY_ALIAS = "AES_DEMO"
        private const val AES_ALGORITHM = "AES"
    }
}
