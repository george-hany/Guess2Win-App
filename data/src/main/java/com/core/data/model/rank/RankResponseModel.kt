package com.core.data.model.rank

import com.google.gson.annotations.SerializedName

data class RankResponseModel(
    @SerializedName("ranks")
    var ranks: List<Rank?>? = null
) {
    data class Rank(
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("rank")
        var rank: String? = null
    )
}