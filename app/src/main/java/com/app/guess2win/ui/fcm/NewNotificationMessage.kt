package com.app.guess2win.ui.fcm

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.core.data.model.fcm.NotificationModel
import com.core.utils.AppConstant.NOTIFICATION_KEY
import com.core.utils.AppConstant.NOTIFICATION_TAG
import java.util.*

class NewNotificationMessage {

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     *
     *
     * TODO: Customize this method's arguments to present relevant content in
     * the notification.
     *
     *
     * TODO: Customize the contents of this method to tweak the behavior and
     * presentation of new message notifications. Make
     * sure to follow the
     * [
 * Notification design guidelines](https://developer.android.com/design/patterns/notifications.html) when doing so.
     *
     * @see .cancel
     */
    companion object {
        private var i = 0
        fun notify(
            context: Context,
            intent: Intent,
            notificationModel: NotificationModel,
            imageId: Int
        ) {
            val res = context.resources
            val nm = context
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channelId = "channel-01"
            val channelName = "Channel Name"
            val importance = NotificationManager.IMPORTANCE_HIGH
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val mChannel = NotificationChannel(
                    channelId, channelName, importance
                )
                mChannel.enableLights(true)
                mChannel.enableVibration(true)
                nm.createNotificationChannel(mChannel)
            }
            // This image is used as the notification's large icon (thumbnail).
            // TODO: Remove this if your notification has no relevant thumbnail.
            val picture =
                BitmapFactory.decodeResource(res, imageId)
            val title = notificationModel.title
            val text = notificationModel.body
            intent.putExtra(NOTIFICATION_KEY, notificationModel)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            val requestID = System.currentTimeMillis().toInt()
            val pendingIntent = PendingIntent.getActivity(
                context,
                requestID,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val builder =
                NotificationCompat.Builder(
                    context,
                    channelId
                ) // Set appropriate defaults for the notification light, sound,
                    // and vibration.
                    .setDefaults(Notification.DEFAULT_ALL) // Set required fields, including the small icon, the
                    // notification title, and text.
                    .setSmallIcon(imageId)
                    .setContentTitle(title)
                    .setContentText(text) // All fields below this line are optional.
                    // Use a default priority (recognized on devices running Android
                    // 4.1 or later)
                    .setPriority(NotificationCompat.PRIORITY_HIGH) // Provide a large icon, shown with the notification in the
                    // notification drawer on devices running Android 3.0 or later.
                    .setLargeIcon(picture)
                    .setWhen(System.currentTimeMillis()) // Set ticker text (preview) information for this notification.
                    // Show a number. This is useful when stacking notifications of
                    // a single type.
                    // If this notification relates to a past or upcoming event, you
                    // should set the relevant time information using the setWhen
                    // method below. If this call is omitted, the notification's
                    // timestamp will by set to the time at which it was shown.
                    // TODO: Call setWhen if this notification relates to a past or
                    // upcoming event. The sole argument to this method should be
                    // the notification timestamp in milliseconds.
                    // .setWhen(...)
                    // Set the pending intent to be initiated when the user touches
                    // the notification.
                    .setContentIntent(
                        pendingIntent
                    ) // Show expanded text content on devices running Android 4.1 or
                    // later.
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(text)
                            .setBigContentTitle(title)
                    ) // Example additional actions for this notification. These will
                    // only show on devices running Android 4.1 or later, so you
                    // should ensure that the pendingIntent in this notification's
                    // content intent provides access to the same actions in
                    // another way.
                    .setWhen(
                        Calendar.getInstance().timeInMillis
                    ) // Automatically dismiss the notification when it is touched.
                    .setAutoCancel(true)
            notify(
                nm,
                builder.build()
            )
        }

        @TargetApi(Build.VERSION_CODES.ECLAIR)
        private fun notify(
            nm: NotificationManager,
            notification: Notification
        ) {
            nm.notify(NOTIFICATION_TAG, i++, notification)
        }

        @TargetApi(Build.VERSION_CODES.ECLAIR)
        fun cancel(context: Context) {
            val nm = context
                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.cancel(
                NOTIFICATION_TAG,
                i
            )
        }
    }
}