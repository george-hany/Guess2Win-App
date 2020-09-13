package com.core.data.model.profile

import com.google.gson.annotations.SerializedName

class ChangePhoneRequest(
    @SerializedName("ClientId")
    var clientId: String? = null,
    @SerializedName("Image")
    var image: String? = null,
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("Phone")
    var phone: String? = null
)