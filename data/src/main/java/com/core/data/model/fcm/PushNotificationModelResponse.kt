package com.core.data.model.fcm

import com.google.gson.annotations.SerializedName

class PushNotificationModelResponse {

    @SerializedName("imageurl")
    var imageurl: String? = null
    @SerializedName("Exhibtors")
    var exhibtors: String? = null
    @SerializedName("NotificationID")
    var notificationID: String? = ""
    @SerializedName("Type")
    var type: String? = ""
    @SerializedName("body")
    var body: String? = ""
    @SerializedName("sound")
    var sound: String? = ""
    @SerializedName("title")
    var title: String? = ""
    @SerializedName("CustomID")
    var customID: String? = ""
    @SerializedName("dateTime")
    var dateTime: String? = ""
}
