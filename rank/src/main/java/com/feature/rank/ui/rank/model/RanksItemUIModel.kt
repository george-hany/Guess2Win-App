package com.feature.rank.ui.rank.model

import com.core.data.model.rank.RankResponseModel

class RanksItemUIModel(var id: String?, var name: String?, var image: String?, var rank: String?) {
    companion object {
        fun mapResponseToUI(model: RankResponseModel.Rank): RanksItemUIModel {
            model.run {
                return RanksItemUIModel(id, name, image, rank)
            }
        }
    }
}