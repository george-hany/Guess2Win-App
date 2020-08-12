package com.feature.matches.ui.matches.model

import android.os.Parcel
import android.os.Parcelable
import com.core.data.model.matches.MatchesResponseModel
import com.core.utils.CommonUtils.getTime

class MatchItemUIModel(
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
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(matchId)
        parcel.writeString(firstTeamImage)
        parcel.writeString(firstTeamName)
        parcel.writeString(firstTeamScore)
        parcel.writeString(secondTeamImage)
        parcel.writeString(secondTeamName)
        parcel.writeString(secondTeamScore)
        parcel.writeString(matchTime)
        parcel.writeString(pridictionNumberGoalsOfTeam1)
        parcel.writeString(pridictionNumberGoalsOfTeam2)
        parcel.writeString(points)
        parcel.writeValue(isMatchStarted)
        parcel.writeValue(isMatchEnded)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchItemUIModel> {
        override fun createFromParcel(parcel: Parcel): MatchItemUIModel {
            return MatchItemUIModel(parcel)
        }

        override fun newArray(size: Int): Array<MatchItemUIModel?> {
            return arrayOfNulls(size)
        }
        fun mapResponseToUI(model: MatchesResponseModel.Data): MatchItemUIModel {
            model.run {
                return MatchItemUIModel(
                    match?.id,
                    match?.firstTeam?.image,
                    match?.firstTeam?.name,
                    match?.noOfFirstTeamGoals,
                    match?.secondTeam?.image,
                    match?.secondTeam?.name,
                    match?.noOfSecondTeamGoals,
                    getTime(match?.time ?: ""),
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