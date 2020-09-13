package com.core.data.model.leaguesRank

data class LeaguesRankResponseModel(
    var id: Int? = null,
    var priodical: Priodical? = null,
    var priodicalID: Int? = null,
    var score: String? = null,
    var user: User? = null,
    var userID: String? = null
) {
    data class Priodical(
        var endDate: String? = null,
        var id: Int? = null,
        var image: String? = null,
        var nameAR: String? = null,
        var nameEn: String? = null,
        var startDate: String? = null,
        var teams: Any? = null
    )

    data class User(
        var accessFailedCount: Int? = null,
        var clientId: String? = null,
        var concurrencyStamp: String? = null,
        var email: Any? = null,
        var emailConfirmed: Boolean? = null,
        var id: String? = null,
        var image: String? = null,
        var lockoutEnabled: Boolean? = null,
        var lockoutEnd: Any? = null,
        var normalizedEmail: Any? = null,
        var normalizedUserName: String? = null,
        var passwordHash: Any? = null,
        var phoneNumber: Any? = null,
        var phoneNumberConfirmed: Boolean? = null,
        var securityStamp: String? = null,
        var twoFactorEnabled: Boolean? = null,
        var userName: String? = null
    )
}