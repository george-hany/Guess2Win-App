package com.feature.matches.ui.matchDetails

import com.core.data.model.matchDetails.MatchDetailsResponseModel
import com.core.utils.CommonUtils

class MatchDetailsUIModel(
    var matchId: String?,
    var firstTeamImage: String?,
    var firstTeamName: String?,
    var firstTeamScore: String?,
    var secondTeamImage: String?,
    var secondTeamName: String?,
    var secondTeamScore: String?,
    var matchTime: String?,
    var pridictionNumberGoalsOfTeam1: String?,
    var pridictionNumberGoalsOfTeam2: String?,
    var points: String?,
    var isMatchStarted: Boolean?,
    var isMatchEnded: Boolean?
) {
    companion object {
        fun mapResponseToUI(matchDetailsResponseModel: MatchDetailsResponseModel.Data): MatchDetailsUIModel {
            matchDetailsResponseModel.run {
                return MatchDetailsUIModel(
                    match?.id,
                    match?.firstTeam?.image,
                    match?.firstTeam?.name,
                    match?.noOfFirstTeamGoals,
                    match?.secondTeam?.image,
                    match?.secondTeam?.name,
                    match?.noOfSecondTeamGoals,
                    CommonUtils.getTime(match?.time ?: ""),
                    pridictionNumberGoalsOfTeam1,
                    pridictionNumberGoalsOfTeam2,
                    point,
                    match?.isStarted,
                    match?.isEnded
                )
            }
        }
    }
}