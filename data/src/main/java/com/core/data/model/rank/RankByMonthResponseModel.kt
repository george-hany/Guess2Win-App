package com.core.data.model.rank

import com.google.gson.annotations.SerializedName

data class RankByMonthResponseModel(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("month")
    var month: Month? = null,
    @SerializedName("monthID")
    var monthID: Int? = null,
    @SerializedName("score")
    var score: String? = null,
    @SerializedName("user")
    var user: User? = null,
    @SerializedName("userID")
    var userID: String? = null
) {
    data class Month(
        @SerializedName("endMonth")
        var endMonth: String? = null,
        @SerializedName("id")
        var id: Int? = null,
        @SerializedName("startMonth")
        var startMonth: String? = null
    )

    data class User(
        @SerializedName("accessFailedCount")
        var accessFailedCount: Int? = null,
        @SerializedName("clientId")
        var clientId: String? = null,
        @SerializedName("concurrencyStamp")
        var concurrencyStamp: String? = null,
        @SerializedName("email")
        var email: Any? = null,
        @SerializedName("emailConfirmed")
        var emailConfirmed: Boolean? = null,
        @SerializedName("id")
        var id: String? = null,
        @SerializedName("image")
        var image: String? = null,
        @SerializedName("lockoutEnabled")
        var lockoutEnabled: Boolean? = null,
        @SerializedName("lockoutEnd")
        var lockoutEnd: Any? = null,
        @SerializedName("normalizedEmail")
        var normalizedEmail: Any? = null,
        @SerializedName("normalizedUserName")
        var normalizedUserName: String? = null,
        @SerializedName("passwordHash")
        var passwordHash: Any? = null,
        @SerializedName("phoneNumber")
        var phoneNumber: Any? = null,
        @SerializedName("phoneNumberConfirmed")
        var phoneNumberConfirmed: Boolean? = null,
        @SerializedName("securityStamp")
        var securityStamp: String? = null,
        @SerializedName("twoFactorEnabled")
        var twoFactorEnabled: Boolean? = null,
        @SerializedName("userName")
        var userName: String? = null
    )
}