package com.imgur.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.base.extensions.MutableSafeLiveData
import com.imgur.core_api.Constants
import com.imgur.core_api.datastore.UserPreferences
import com.imgur.core_api.models.ImgurAccessToken
import com.imgur.core_api.navigation.OverlayNavRouter
import kotlinx.coroutines.launch
import javax.inject.Inject

interface OnImgurTokenReceivedListener {
    fun onTokenReceived(token: ImgurAccessToken)
}

class LoginViewModel @Inject constructor(
    private val router: OverlayNavRouter,
    private val preferences: UserPreferences
) : ViewModel(), OnImgurTokenReceivedListener {

    private var token = ImgurAccessToken.EMPTY

    val authLink = MutableSafeLiveData("")

    fun onBackClick() {
        if (token.isEmpty()) {
            router.finish()
        } else {
            router.openMainScreen()
        }
    }

    fun onLoginClick() {
        authLink.value = Constants.imgurAuthUrl
    }

    override fun onTokenReceived(token: ImgurAccessToken) {
        this.token = token

        viewModelScope.launch {
            preferences.saveTokens(token.accessToken, token.refreshToken)

            onBackClick()
        }
    }
}
