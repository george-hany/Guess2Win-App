package com.core.data.model.fcm

import android.os.Parcel
import android.os.Parcelable
import java.text.SimpleDateFormat
import java.util.*

class NotificationModel(
    var notificationId: String?,
    var title: String?,
    var body: String?,
    var type: String?,
    var customId: String?,
    var time: String?
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<NotificationModel> =
            object : Parcelable.Creator<NotificationModel> {
                override fun createFromParcel(source: Parcel): NotificationModel =
                    NotificationModel(source)

                override fun newArray(size: Int): Array<NotificationModel?> = arrayOfNulls(size)
            }

        fun createNotificationrModel(it: NotificationModelResponse.ResultBean.ItemsBean): NotificationModel {
            return NotificationModel(
                it.notificationID,
                it.name,
                it.body,
                it.type,
                it.customID,
                formattedTime(
                    it.insertDate!!
                )
            )
        }

        fun createNotificationModelFromPush(pushNotificationModelResponse: PushNotificationModelResponse): NotificationModel {
            return NotificationModel(
                pushNotificationModelResponse.notificationID,
                pushNotificationModelResponse.title,
                pushNotificationModelResponse.body,
                pushNotificationModelResponse.type,
                pushNotificationModelResponse.customID,
                pushNotificationModelResponse.dateTime!!
            )
        }

        private fun formattedTime(notificationTime: String): String {
            var date = notificationTime
            // 2019-09-10T14:25:02.957
            var spf = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
            val newDate = spf.parse(date)

            spf = SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.ENGLISH)
            date = spf.format(newDate)
            return date
        }
    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(notificationId)
        writeString(title)
        writeString(body)
        writeString(type)
        writeString(customId)
        writeString(time)
    }
}