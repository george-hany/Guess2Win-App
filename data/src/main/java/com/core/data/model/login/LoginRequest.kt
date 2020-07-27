package com.core.data.model.login

import com.google.gson.annotations.SerializedName

class LoginRequest(
    @SerializedName("first_name")
    var firstName: String? = null,

    @SerializedName("last_name")
    var lastName: String? = null,

    @SerializedName("id")
    var id: String? = null,

    var userImage: String? = null
)
