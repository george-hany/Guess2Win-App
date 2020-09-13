package com.core.data.model.fcm

import com.google.gson.annotations.SerializedName

class FcmModelRequest {
    @SerializedName("DeviceType")
    var deviceType: String? = null
    @SerializedName("Reg")
    var reg: String? = null
}
