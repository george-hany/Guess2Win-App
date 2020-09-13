package com.core.data.model.rank

import com.google.gson.annotations.SerializedName

data class MonthResponseModel(
    @SerializedName("endMonth")
    var endMonth: String? = null,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("startMonth")
    var startMonth: String? = null
)