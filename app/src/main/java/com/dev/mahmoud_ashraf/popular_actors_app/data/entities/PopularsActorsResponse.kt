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
{
    data class Actor(  @SerializedName("gender")
                       val gender: Int?,
                       @SerializedName("id")
                       val id: Int?,
                       @SerializedName("known_for_department")
                       val knownForDepartment: String?,
                       @SerializedName("name")
                       val name: String?,
                       @SerializedName("profile_path")
                       val profilePath: String?)
}

