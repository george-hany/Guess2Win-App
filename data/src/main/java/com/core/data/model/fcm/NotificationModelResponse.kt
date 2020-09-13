package com.core.data.model.fcm

import com.google.gson.annotations.SerializedName

class NotificationModelResponse {

    @SerializedName("version")
    var version: String? = null
    @SerializedName("statusCode")
    var statusCode: Int = 0
    @SerializedName("message")
    var message: String? = null
    @SerializedName("result")
    var result: ResultBean? = null

    class ResultBean {
        @SerializedName("paging")
        var paging: PagingBean? = null
        @SerializedName("links")
        var links: List<LinksBean>? = null
        @SerializedName("items")
        var items: List<ItemsBean>? = null

        class PagingBean {
            @SerializedName("totalItems")
            var totalItems: Int = 0
            @SerializedName("pageNumber")
            var pageNumber: Int = 0
            @SerializedName("pageSize")
            var pageSize: Int = 0
            @SerializedName("totalPages")
            var totalPages: Int = 0
        }

        class LinksBean {
            @SerializedName("href")
            var href: String? = null
            @SerializedName("rel")
            var rel: String? = null
            @SerializedName("method")
            var method: String? = null
        }

        class ItemsBean {
            @SerializedName("notificationID")
            var notificationID: String? = null
            @SerializedName("applicationID")
            var applicationID: String? = null
            @SerializedName("placeID")
            var placeID: Any? = null
            @SerializedName("name")
            var name: String? = null
            @SerializedName("body")
            var body: String? = null
            @SerializedName("type")
            var type: String? = null
            @SerializedName("imagePath")
            var imagePath: String? = null
            @SerializedName("hrefURL")
            var hrefURL: Any? = null
            @SerializedName("scheduleDate")
            var scheduleDate: Any? = null
            @SerializedName("detectOnly")
            var detectOnly: Any? = null
            @SerializedName("status")
            var isStatus: Boolean = false
            @SerializedName("insertDate")
            var insertDate: String? = null
            @SerializedName("customID")
            var customID: String? = null
            @SerializedName("applicationUserNotification")
            var applicationUserNotification: Any? = null
        }
    }
}
