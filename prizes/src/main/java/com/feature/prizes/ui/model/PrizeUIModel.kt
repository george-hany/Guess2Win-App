package com.feature.prizes.ui.model

import com.core.data.model.prizes.PrizesResponse

class PrizeUIModel(
    var description: String?,
    var type: Int?,
    var fromPoint: String?,
    var toPoint: String?
) {
    companion object {
        fun mapResponseToUI(prize: PrizesResponse.Data): PrizeUIModel {
            prize.run {
                return PrizeUIModel(name, type, fromPoint.toString(), toPoint.toString())
            }
        }
    }
}