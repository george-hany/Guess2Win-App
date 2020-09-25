package com.core.data.model.rank

import com.google.gson.annotations.SerializedName

data class MonthResponseModel(
    @SerializedName("data")
    var `data`: List<Data?>? = null
) {
    data class Data(
        @SerializedName("endMonth")
        var endMonth: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("startMonth")
        var startMonth: String? = null
    )
}