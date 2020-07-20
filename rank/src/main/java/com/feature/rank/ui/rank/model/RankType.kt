package com.feature.rank.ui.rank.model

import android.os.Parcel
import android.os.Parcelable

enum class RankType() : Parcelable {
    WEEK, MONTH, YEAR;

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RankType> {
        override fun createFromParcel(parcel: Parcel): RankType {
            return valueOf(parcel.readString() ?: "")
        }

        override fun newArray(size: Int): Array<RankType?> {
            return arrayOfNulls(size)
        }
    }
}