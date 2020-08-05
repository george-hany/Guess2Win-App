package com.core.data.model.login

import com.google.gson.annotations.SerializedName

class LoginRequest {
    @SerializedName("Name")
    var Name: String = ""

    @SerializedName("ClientId")
    var ClientId: String = ""

    @SerializedName("Image")
    var Image: String = ""
}
