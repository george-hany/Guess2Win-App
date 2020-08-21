package com.core.data.model.extraPoints

import com.google.gson.annotations.SerializedName

data class CheckWatchAdAvailabilityResponseModel(
    @SerializedName("data")
    var `data`: Boolean? = null
)