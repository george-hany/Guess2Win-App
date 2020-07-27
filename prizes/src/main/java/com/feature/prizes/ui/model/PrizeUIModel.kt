package com.feature.prizes.ui.model

import com.core.data.model.prizes.PrizesResponse

class PrizeUIModel(var description: String?, var image: String?, var type: String?) {
    companion object {
        fun mapResponseToUI(prize: PrizesResponse.Prize): PrizeUIModel {
            prize.run {
                return PrizeUIModel(description, image, type)
            }
        }
    }
}