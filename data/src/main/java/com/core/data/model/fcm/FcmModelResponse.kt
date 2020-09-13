package com.core.data.model.fcm

import com.google.gson.annotations.SerializedName

class FcmModelResponse {
    @SerializedName("userID")
    var userID: String? = null

    @SerializedName("applicationID")
    var applicationID: String? = null

    @SerializedName("deviceType")
    var deviceType: String? = null

    @SerializedName("reg")
    var reg: String? = null

    @SerializedName("insertDate")
    var insertDate: Any? = null
}
