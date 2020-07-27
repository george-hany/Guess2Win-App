package com.feature.matches.ui.matches.model

import android.os.Parcel
import android.os.Parcelable

class MatchesFragmentModel(var type: MatchesType, var leagueId: String? = null) : Parcelable {
    constructor(parcel: Parcel) : this(
        MatchesType.valueOf(parcel.readString() ?: ""),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(type, flags)
        parcel.writeString(leagueId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchesFragmentModel> {
        override fun createFromParcel(parcel: Parcel): MatchesFragmentModel {
            return MatchesFragmentModel(parcel)
        }

        override fun newArray(size: Int): Array<MatchesFragmentModel?> {
            return arrayOfNulls(size)
        }
    }
}

enum class MatchesType() : Parcelable {
    ALL, LEAGUE;

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MatchesType> {
        override fun createFromParcel(parcel: Parcel): MatchesType {
            return MatchesType.valueOf(parcel.readString() ?: "")
        }

        override fun newArray(size: Int): Array<MatchesType?> {
            return arrayOfNulls(size)
        }
    }
}