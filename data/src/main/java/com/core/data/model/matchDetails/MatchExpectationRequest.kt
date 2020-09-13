package com.core.data.model.matchDetails

import com.google.gson.annotations.SerializedName

class MatchExpectationRequest {
    @SerializedName("MatchID")
    var matchId: Int? = null

    @SerializedName("NumberGoolOfTeem1")
    var firstTeamScore: Int? = null

    @SerializedName("NumberGoolOfTeem2")
    var secondTeamScore: Int? = null

    @SerializedName("UserID")
    var userID: String? = null
}