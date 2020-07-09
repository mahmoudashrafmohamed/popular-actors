package com.dev.mahmoud_ashraf.popular_actors_app.data.entities

import com.google.gson.annotations.SerializedName

data class PopularsActorsResponse (@SerializedName("page")
                           val page: Int?,
                                   @SerializedName("results")
                           val peopleList: List<Actor>?,
                                   @SerializedName("total_pages")
                           val totalPages: Int?,
                                   @SerializedName("total_results")
                           val totalResults: Int?)


