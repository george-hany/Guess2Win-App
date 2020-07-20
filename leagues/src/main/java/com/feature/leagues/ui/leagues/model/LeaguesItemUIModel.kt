package com.feature.leagues.ui.leagues.model

import android.os.Parcel
import android.os.Parcelable
import com.core.data.model.leagues.LeaguesListResponseModel

class LeaguesItemUIModel(var id: String?, var name: String?, var image: String?) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(id)
        dest?.writeString(name)
        dest?.writeString(image)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<LeaguesItemUIModel> {
        override fun createFromParcel(parcel: Parcel): LeaguesItemUIModel {
            return LeaguesItemUIModel(parcel)
        }

        override fun newArray(size: Int): Array<LeaguesItemUIModel?> {
            return arrayOfNulls(size)
        }
        fun mapResponseToUI(league: LeaguesListResponseModel.Leagues): LeaguesItemUIModel {
            league.run {
                return LeaguesItemUIModel(id, name, image)
            }
        }
    }
}