package com.dev.mahmoud_ashraf.popular_actors_app.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(  @SerializedName("gender")
                   val gender: Int?,
                   @SerializedName("id")
                   val id: Int?,
                   @SerializedName("known_for_department")
                   val knownForDepartment: String?,
                   @SerializedName("name")
                   val name: String?,
                   @SerializedName("profile_path")
                   val profilePath: String?,
                   @SerializedName("known_for")
                   val knownFor: List<ActorInfo>?

) : Parcelable

