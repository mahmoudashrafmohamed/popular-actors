package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.preview

import android.Manifest
import android.R.attr.path
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.dev.mahmoud_ashraf.popular_actors_app.R
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.core.getExternalDir
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.details.DetailsFragment
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home.HomeViewModel
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.preview_image_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.FileOutputStream
import java.io.IOException


class PreviewImageFragment : Fragment() {

    private val viewModel by viewModel<PreviewImageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.preview_image_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let {
            val id = it.getString(DetailsFragment.ARGS_ID)
            val url = it.getString(DetailsFragment.ARGS_IMAGE_URL)
            Glide.with(image).load(url).into(image)
            setupListeners(url, id)
        }
    }

    private fun setupListeners(url: String?, id: String?) {
        save_button.setOnClickListener {
            viewModel.download(
                url = url.orEmpty(),
                fileName = "${id.orEmpty()}.jpg",
                externalDirectory = getExternalDir(requireContext())
            )
        }
    }


}

