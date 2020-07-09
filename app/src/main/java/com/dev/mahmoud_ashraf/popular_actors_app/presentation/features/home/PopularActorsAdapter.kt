package com.dev.mahmoud_ashraf.popular_actors_app.presentation.features.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.mahmoud_ashraf.popular_actors_app.data.entities.Actor
import com.dev.mahmoud_ashraf.popular_actors_app.databinding.ActorItemViewBinding
import timber.log.Timber

class PopularActorsAdapter :
    ListAdapter<Actor, PopularActorsAdapter.ActorViewHolder>(ActorsDiffCallback()) {

    var onItemClicked: ((position: Int, actor: Actor) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            ActorItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actorListItem = getItem(position)
        holder.bind(actorListItem, onItemClicked, position)
    }

    class ActorViewHolder(
        private val binding: ActorItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            actor: Actor,
            onItemClicked: ((position: Int, actor: Actor) -> Unit)?,
            position: Int
        ) {
            binding.nameText.text = actor.name

            Glide.with(binding.profileImage)
                .load(actor.profilePath)
                .override(300)
                .thumbnail(0.1f)
                .into(binding.profileImage)

            binding.itemCardView.setOnClickListener {
                onItemClicked?.invoke(position, actor)
            }

            binding.executePendingBindings()


        }
    }

    class ActorsDiffCallback : DiffUtil.ItemCallback<Actor>() {
        override fun areItemsTheSame(
            oldItem: Actor,
            newItem: Actor
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Actor,
            newItem: Actor
        ): Boolean {
            return oldItem == newItem
        }


    }
}