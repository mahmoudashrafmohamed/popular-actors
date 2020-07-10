package com.dev.mahmoud_ashraf.popular_actors_app.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularsActorsResponse (@SerializedName("page")
                           val page: Int?,
                                   @SerializedName("results")
                           val peopleList: List<Actor>?,
                                   @SerializedName("total_pages")
                           val totalPages: Int?,
                                   @SerializedName("total_results")
                           val totalResults: Int?) : Parcelable


