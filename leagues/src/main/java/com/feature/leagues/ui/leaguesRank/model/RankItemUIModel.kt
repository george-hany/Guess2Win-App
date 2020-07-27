package com.feature.leagues.ui.leaguesRank.model

import com.core.data.model.leaguesRank.LeaguesRankResponseModel

class RankItemUIModel(var id: String?, var name: String?, var image: String?, var rank: String?) {
    companion object {
        fun mapResponseToUI(model: LeaguesRankResponseModel.Rank): RankItemUIModel {
            model.run {
                return RankItemUIModel(id, name, image, rank)
            }
        }

    }
}