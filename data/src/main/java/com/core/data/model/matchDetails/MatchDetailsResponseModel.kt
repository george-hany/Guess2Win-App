package com.core.data.model.matchDetails

import com.google.gson.annotations.SerializedName

class MatchDetailsResponseModel(
    @SerializedName("data")
    var `data`: Data? = null
) {
    data class Data(
        @SerializedName("match")
        var match: Match? = null,
        @SerializedName("point")
        var point: String? = null,
        @SerializedName("pridictionNumberGoalsOfTeam1")
        var pridictionNumberGoalsOfTeam1: String? = null,
        @SerializedName("pridictionNumberGoalsOfTeam2")
        var pridictionNumberGoalsOfTeam2: String? = null
    ) {
        data class Match(
            @SerializedName("date")
            var date: String? = null,
            @SerializedName("firstTeam")
            var firstTeam: FirstTeam? = null,
            @SerializedName("id")
            var id: String? = null,
            @SerializedName("isEnded")
            var isEnded: Boolean? = null,
            @SerializedName("isStarted")
            var isStarted: Boolean? = null,
            @SerializedName("league")
            var league: League? = null,
            @SerializedName("noOfFirstTeamGoals")
            var noOfFirstTeamGoals: String? = null,
            @SerializedName("noOfSecondTeamGoals")
            var noOfSecondTeamGoals: String? = null,
            @SerializedName("secondTeam")
            var secondTeam: SecondTeam? = null,
            @SerializedName("time")
            var time: String? = null
        ) {
            data class FirstTeam(
                @SerializedName("country")
                var country: String? = null,
                @SerializedName("id")
                var id: Int? = null,
                @SerializedName("image")
                var image: String? = null,
                @SerializedName("name")
                var name: String? = null
            )

            data class League(
                @SerializedName("endDate")
                var endDate: String? = null,
                @SerializedName("id")
                var id: Int? = null,
                @SerializedName("image")
                var image: String? = null,
                @SerializedName("name")
                var name: String? = null,
                @SerializedName("startDate")
                var startDate: String? = null
            )

            data class SecondTeam(
                @SerializedName("country")
                var country: String? = null,
                @SerializedName("id")
                var id: Int? = null,
                @SerializedName("image")
                var image: String? = null,
                @SerializedName("name")
                var name: String? = null
            )
        }
    }
}