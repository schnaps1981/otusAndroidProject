package com.imgur.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.imgur.core_api.viewmodel.ViewModelFactory
import com.imgur.login.databinding.FmtLoginBinding
import com.imgur.login.di.LoginComponent
import javax.inject.Inject

class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel: LoginViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FmtLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LoginComponent.create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fmt_login, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    companion object {
        val TAG: String = LoginFragment::class.java.simpleName

        fun newInstance(): LoginFragment {
            return LoginFragment().apply {

            }
        }
    }
}
