package com.imgur.login

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.imgur.core_api.AppRootProvider
import com.imgur.core_api.Constants
import com.imgur.core_api.models.ImgurAccessToken
import com.imgur.core_api.viewmodel.ViewModelFactory
import com.imgur.login.databinding.FmtLoginBinding
import com.imgur.login.di.LoginComponent
import javax.inject.Inject


class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FmtLoginBinding

    private val imgurAppRedirect = Constants.imgurAppRedirect

    private var onTokenReceivedListener: OnImgurTokenReceivedListener? = null

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.onBackClick()
        }
    }

    private val webViewClient = object : WebViewClient() {

        private var handledToken: ImgurAccessToken = ImgurAccessToken.EMPTY

        private fun isAuthRedirect(uri: Uri): Boolean {
            val fragment = uri.fragment ?: return false
            val url = uri.buildUpon().fragment("").build()

            return fragment.isNotEmpty() && url.toString() == imgurAppRedirect
        }

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            request?.let {
                if (isAuthRedirect(it.url)) {
                    val token = ImgurAccessToken.valueOf(it.url.fragment)

                    if (handledToken.isEmpty() || handledToken != token) {
                        handledToken = token

                        binding.loginWebView.stopLoading()
                        binding.loginWebView.loadUrl("about:blank")

                        clearHistoryAndCookies()

                        onTokenReceivedListener?.onTokenReceived(token)
                    }
                }
            }

            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LoginComponent.create((requireActivity().application as AppRootProvider).getRootProvider())
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fmt_login, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        with(binding.loginWebView) {
            webViewClient = this@LoginFragment.webViewClient

            settings.apply {
                javaScriptEnabled = true
            }
        }

        onTokenReceivedListener = viewModel

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authLink.observe(viewLifecycleOwner) {
            if (it.isNotBlank()) {
                binding.loginWebView.loadUrl(it)
            }
        }
    }

    private fun clearHistoryAndCookies() {
        with(binding.loginWebView) {
            clearCache(true)
            clearHistory()
            clearCookies()
        }
    }

    private fun clearCookies() {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
    }

    override fun onDetach() {
        super.onDetach()

        with(binding.loginWebView) {
            clearHistoryAndCookies()

            destroy()
        }
    }

    companion object {
        val TAG: String = LoginFragment::class.java.simpleName

        fun newInstance(): LoginFragment {
            return LoginFragment().apply {

            }
        }
    }
}
