package com.feature.help.ui.model

import com.core.data.model.help.HelpResponseModel

class HelpItemModel(
    var id: String?,
    var title: String?,
    var description: String?
) {
    companion object {
        fun mapResponseToUI(model: HelpResponseModel.Data): HelpItemModel {
            model.run {
                return HelpItemModel(id.toString(), title, description)
            }
        }
    }
}