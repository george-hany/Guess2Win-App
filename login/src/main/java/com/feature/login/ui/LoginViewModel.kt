package com.feature.login.ui

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.core.base.BaseViewModel
import com.core.data.repos.LoginRepo
import com.facebook.AccessToken
import com.facebook.GraphRequest
import org.json.JSONException
import com.core.data.model.login.LoginRequest
import com.google.firebase.messaging.FirebaseMessaging

class LoginViewModel(private val loginRepo: LoginRepo) : BaseViewModel<LoginRepo>(loginRepo) {
    val loginFinished = MutableLiveData(false)
    var loginMediatorLiveData: MediatorLiveData<Any> = MediatorLiveData()
    val loginRequest = LoginRequest()

    fun getUserProfile(currentAccessToken: AccessToken) {
        val request = GraphRequest.newMeRequest(
            currentAccessToken
        ) { `object`, _ ->
            try {
                val firstName = `object`.getString("first_name")
                val lastName = `object`.getString("last_name")
                val id = `object`.getString("id")
                val imageUrl =
                    "https://graph.facebook.com/$id/picture?type=normal"
                loginRequest.Name = "$firstName $lastName"
                loginRequest.ClientId = id
                loginRequest.Image = imageUrl
                subscribeToTopic()
            } catch (e: JSONException) {
                message.value = e.printStackTrace()
                setIsLoading(false)
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "first_name,last_name,email,id")
        request.parameters = parameters
        request.executeAsync()
    }

    fun login(loginRequest: LoginRequest) {
        val requestLogin = loginRepo.login(loginRequest)
        loginMediatorLiveData.addSource(requestLogin) {
            loginRepo.saveLoginResponseInSharedPref(it)
            loginFinished.value = true
            setIsLoading(false)
        }
    }

    fun isLoggedIn() = loginRepo.isLoggedIn()

    fun subscribeToTopic() {
        FirebaseMessaging.getInstance().subscribeToTopic("global")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    login(loginRequest)
                } else {
                    message.value = task.exception?.message
                    setIsLoading(false)
                }
            }
    }
}
