package com.core.data.model.login

import com.google.gson.annotations.SerializedName

class LoginResponse(
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("user")
    var user: User? = null
) {
    data class User(
        @SerializedName("clientId")
        var clientId: String? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("phoneNumber")
        var phoneNumber: String? = null,
        @SerializedName("userName")
        var userName: String? = null
    )
}