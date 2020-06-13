package com.core.data

class MainExceptions(var exception: String?) : Exception() {

    enum class ExceptionsTypes(var string: String) {
        NO_INTERNET_CONNECTIVITY("No Internet Connection")
    }
}
