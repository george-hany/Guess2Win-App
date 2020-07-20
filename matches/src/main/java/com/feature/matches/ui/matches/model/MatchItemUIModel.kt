package com.feature.matches.ui.matches.model

import com.core.data.model.matches.MatchesResponseModel

class MatchItemUIModel(
    var matchId: String?,
    var firstTeamImage: String?,
    var firstTeamName: String?,
    var firstTeamScore: String?,
    var secondTeamImage: String?,
    var secondTeamName: String?,
    var secondTeamScore: String?,
    var matchTime: String?,
    var userExpectation: String?,
    var points: String?,
    var isMatchStarted: Boolean?,
    var isMatchEnded: Boolean?
) {
    companion object {
        fun mapResponseToUI(match: MatchesResponseModel.Result.Match): MatchItemUIModel {
            match.run {
                return MatchItemUIModel(
                    matchId,
                    firstTeamImage,
                    firstTeamName,
                    firstTeamScore,
                    secondTeamImage,
                    secondTeamName,
                    secondTeamScore,
                    matchTime,
                    userExpectation,
                    points,
                    isMatchStarted,
                    isMatchEnded
                )
            }
        }
    }
}