package com.core.data.model.prizes

import com.google.gson.annotations.SerializedName

data class PrizesResponse(
    @SerializedName("prizes")
    var prizes: List<Prize?>? = null
) {
    data class Prize(
        @SerializedName("description")
        var description: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("type")
        var type: String? = null
    )
}