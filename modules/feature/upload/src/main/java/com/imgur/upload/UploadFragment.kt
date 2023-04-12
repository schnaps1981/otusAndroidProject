package com.imgur.upload

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.imgur.core_api.AppRootProvider
import com.imgur.upload.databinding.FmtUploadBinding
import com.imgur.upload.di.UploadComponent
import com.imgur.upload.list.AccountImagesItemAdapter
import javax.inject.Inject

class UploadFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: UploadViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FmtUploadBinding

    @Inject
    lateinit var adapter: AccountImagesItemAdapter

    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                requireContext().contentResolver.takePersistableUriPermission(uri, flag)

                viewModel.chooseImage(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootProvider = (requireActivity().application as AppRootProvider).getRootProvider()
        UploadComponent.create(requireContext(), this, rootProvider, rootProvider).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fmt_upload, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.imageChooser.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        with(binding.uploadItemsList) {
            adapter = this@UploadFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.items.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        val TAG: String = UploadFragment::class.java.simpleName

        fun newInstance(): UploadFragment {
            return UploadFragment().apply {

            }
        }
    }
}
