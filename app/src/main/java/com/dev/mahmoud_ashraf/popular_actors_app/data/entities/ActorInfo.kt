package com.dev.mahmoud_ashraf.popular_actors_app.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActorInfo(
    @SerializedName("poster_path")
    val posterPath : String?
) : Parcelable