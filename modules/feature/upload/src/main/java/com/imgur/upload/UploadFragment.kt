package com.imgur.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.imgur.upload.databinding.FmtUploadBinding
import com.imgur.upload.di.UploadComponent
import javax.inject.Inject

class UploadFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FmtUploadBinding

    private val viewModel: UploadViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        UploadComponent.create().inject(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    companion object {
        val TAG: String = UploadFragment::class.java.simpleName

        fun newInstance(): UploadFragment {
            return UploadFragment().apply {

            }
        }
    }
}