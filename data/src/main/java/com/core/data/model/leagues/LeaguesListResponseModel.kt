package com.core.data.model.leagues

import com.google.gson.annotations.SerializedName

data class LeaguesListResponseModel(
    @SerializedName("result")
    var result: List<Leagues?>? = null
) {
    data class Leagues(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("name")
        var name: String? = null
    )
}