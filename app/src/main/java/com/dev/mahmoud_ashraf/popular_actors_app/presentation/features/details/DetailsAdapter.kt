package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.ActorInfo
import com.dev.mahmoud_ashraf.popular_actors_app.databinding.ImageItemViewBinding
import com.dev.mahmoud_ashraf.popular_actors_app.domain.usecases.POSTER_BASE_URL

class DetailsAdapter :
    ListAdapter<ActorInfo, DetailsAdapter.ActorInfoViewHolder>(ActorsInfoDiffCallback()) {

    var onItemClicked: ((position: Int, actorImage: ActorInfo) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorInfoViewHolder {
        return ActorInfoViewHolder(
            ImageItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holderInfo: ActorInfoViewHolder, position: Int) {
        val actorListItem = getItem(position)
        holderInfo.bind(actorListItem, onItemClicked, position)
    }

    class ActorInfoViewHolder(
        private val binding: ImageItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            actorInfo : ActorInfo,
            onItemClicked: ((position: Int, actorInfo: ActorInfo) -> Unit)?,
            position: Int
        ) {

            Glide.with(binding.profileImage)
                .load(POSTER_BASE_URL+actorInfo.posterPath)
                .override(300)
                .thumbnail(0.1f)
                .into(binding.profileImage)

            binding.profileImage.setOnClickListener {
                onItemClicked?.invoke(position, actorInfo)
            }

            binding.executePendingBindings()


        }
    }

    class ActorsInfoDiffCallback : DiffUtil.ItemCallback<ActorInfo>() {
        override fun areItemsTheSame(
            oldItem: ActorInfo,
            newItem: ActorInfo
        ): Boolean {
            return oldItem.posterPath == newItem.posterPath
        }

        override fun areContentsTheSame(
            oldItem: ActorInfo,
            newItem: ActorInfo
        ): Boolean {
            return oldItem == newItem
        }


    }
}