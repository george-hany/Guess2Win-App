package com.app.guess2win.ui.fcm

import android.content.Intent
import android.util.Log
import com.app.guess2win.R
import com.app.guess2win.ui.MainActivity
import com.core.data.constant.SharedPrefKeys.Companion.NOTIFICATION_STATUS
import com.core.data.model.fcm.NotificationModel
import com.core.data.model.fcm.PushNotificationModelResponse
import com.core.prefrence.shared_preferences.AppSharedPreference
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import org.json.JSONObject

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check if message contains a data payload.
        val sharedPref = AppSharedPreference(this, "pref_")
        val notificationIsDeactive = sharedPref.getBoolean(NOTIFICATION_STATUS)
        if (remoteMessage.data.isNotEmpty() && !notificationIsDeactive) {
            val gson = Gson()
            try {
                val json = JSONObject(remoteMessage.data as Map<*, *>)
                Log.e(TAG, "Data Payload: $json")
                val pushNotificationModelResponse = gson.fromJson(
                    json.toString(),
                    PushNotificationModelResponse::class.java
                )
                handleDataMessage(pushNotificationModelResponse)
            } catch (e: Exception) {
                Log.e(TAG, "Exception: " + e.message)
            }
        }
    }

    private fun handleDataMessage(pushNotificationModelResponse: PushNotificationModelResponse) {
        try {
            NotificationModel.createNotificationModelFromPush(pushNotificationModelResponse)
            // app is in splash, show the notification in notification tray
            // check for image attachment
            NewNotificationMessage.notify(
                this,
                Intent(this, MainActivity::class.java),
                NotificationModel.createNotificationModelFromPush(pushNotificationModelResponse),
                R.mipmap.ic_launcher
            )
        } catch (e: Exception) {
            Log.e("Exception: ", e.message)
        }
    }

    companion object {
        private val TAG = MyFirebaseMessagingService::class.java.simpleName
    }
}
