package com.feature.matches.ui.matchDetails

import com.core.data.model.matchDetails.MatchDetailsResponseModel

class MatchDetailsUIModel(
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
        fun mapResponseToUI(matchDetailsResponseModel: MatchDetailsResponseModel): MatchDetailsUIModel {
            matchDetailsResponseModel.run {
                return MatchDetailsUIModel(
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