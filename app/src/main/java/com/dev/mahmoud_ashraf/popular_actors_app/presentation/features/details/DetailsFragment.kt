package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.mahmoud_ashraf.popular_actors_app.R
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.Actor
import com.dev.mahmoud_ashraf.popular_actors_app.databinding.DetailsFragmentBinding
import com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases.POSTER_BASE_URL
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.core.EndlessRecyclerViewScrollListener
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home.HomeActivity
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home.HomeFragment
import com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home.PopularActorsAdapter
import timber.log.Timber

class DetailsFragment : Fragment() {

    private lateinit var binding: DetailsFragmentBinding
    private val adapter by lazy { DetailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecycler()
        arguments?.let {
            val actor = it.getParcelable<Actor>(HomeFragment.ARGS_ACTOR)
            updateUi(actor)
        }
    }

    private fun setUpRecycler() {
        binding.imagesRecycler.adapter = adapter
        binding.imagesRecycler.itemAnimator = null
        adapter.onItemClicked = { _, image ->
            findNavController().navigate(R.id.action_detailsFragment_to_previewImageFragment,
                Bundle().also {
                    it.putString(ARGS_ID, image.id?.toString())
                    it.putString(ARGS_IMAGE_URL, POSTER_BASE_URL.plus(image.posterPath))
                })
        }
    }

    private fun updateUi(actor: Actor?) {
        actor?.let {

            binding.nameText.text = it.name

            Glide.with(binding.profileImage)
                .load(actor.profilePath)
                .override(300)
                .thumbnail(0.1f)
                .into(binding.profileImage)

            adapter.submitList(it.knownFor)

        }
    }

    companion object {
        const val ARGS_ID = "image_id"
        const val ARGS_IMAGE_URL = "image_url"
    }

}