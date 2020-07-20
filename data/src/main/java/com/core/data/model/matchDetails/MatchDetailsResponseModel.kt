package com.core.data.model.matchDetails

import com.google.gson.annotations.SerializedName

class MatchDetailsResponseModel(
    @SerializedName("firstTeamImage")
    var firstTeamImage: String? = null,
    @SerializedName("firstTeamName")
    var firstTeamName: String? = null,
    @SerializedName("firstTeamScore")
    var firstTeamScore: String? = null,
    @SerializedName("isMatchEnded")
    var isMatchEnded: Boolean? = null,
    @SerializedName("isMatchStarted")
    var isMatchStarted: Boolean? = null,
    @SerializedName("matchId")
    var matchId: String? = null,
    @SerializedName("matchTime")
    var matchTime: String? = null,
    @SerializedName("points")
    var points: String? = null,
    @SerializedName("secondTeamImage")
    var secondTeamImage: String? = null,
    @SerializedName("secondTeamName")
    var secondTeamName: String? = null,
    @SerializedName("secondTeamScore")
    var secondTeamScore: String? = null,
    @SerializedName("userExpectation")
    var userExpectation: String? = null
)