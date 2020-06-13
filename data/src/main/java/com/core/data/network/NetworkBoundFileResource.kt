package com.core.data.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import com.core.data.MainExceptions
import com.core.data.strategy.DataStrategy
import com.core.network.NetworkFactory
import com.core.network.NetworkFactoryInterface
import com.core.utils.FileManager
import com.core.network.model.Result

abstract class NetworkBoundFileResource<ResultType : Any>
@MainThread constructor(
    var networkFactory: NetworkFactoryInterface,
    strategy: DataStrategy.Strategies = DataStrategy.Strategies.DEFAULT_STRATEGY,
    var fileName: String = "",
    var fileManager: FileManager?
) {
    private val result = MediatorLiveData<ResultType?>()

    init {
        if (networkFactory.isNetworkConnected()) {
            fetchFromNetwork(strategy)
        } else {
            if (strategy == DataStrategy.Strategies.NETWORK_ONLY) {
                onFetchFailed(
                    MainExceptions(
                        MainExceptions
                            .ExceptionsTypes.NO_INTERNET_CONNECTIVITY.string
                    )
                )
            } else if (strategy == DataStrategy.Strategies.DEFAULT_STRATEGY) {
                loadDataFromDB()
            }
        }
    }

    @MainThread
    private fun setValue(newValue: ResultType?) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dataStrategy: DataStrategy.Strategies) {
        GlobalScope.launch(Dispatchers.Main) {
            val apiResponse = createCall()
            if (networkFactory is NetworkFactory) {
                try {
                    val response =
                        networkFactory.makeRequest(call = apiResponse, errorMessage = "sadsa")
                    when (response) {
                        is Result.Success -> {
                            if (response.data.code() == 200) {
                                if (dataStrategy == DataStrategy.Strategies.NETWORK_ONLY) {
                                    loadDataFromNetwork(response)
                                } else if (dataStrategy == DataStrategy.Strategies.DEFAULT_STRATEGY) {
                                    saveCallResultInDB(processResponse(response).body())
                                    loadDataFromDB()
                                }
                            } else {
                                handleErrorResponseType(response.data)
                                setEmptyObject()
                            }
                        }
                        is Result.Error -> {
                            handleErrorResponseType(response.data)
                            setEmptyObject()
                        }
                        else -> {
                        }
                    }
                } catch (e: Exception) {
                    onFetchFailed(MainExceptions(e.message))
                    setEmptyObject()
                }
            } else {
                val dataWrapper = dataWrapper(convert(networkFactory.getStringJson()))
                result.addSource(dataWrapper) { newData ->
                    result.removeSource(dataWrapper)
                    setValue(newData)
                }
            }
        }
    }

    private fun setEmptyObject() {
        val mutableLiveData = MutableLiveData<ResultType?>()
        mutableLiveData.value = null
        result.addSource(
            mutableLiveData
        ) { newData ->
            setValue(newData)
        }
    }

    private fun loadDataFromNetwork(response: Result.Success<ResultType>) {
        val dataSource = dataWrapper(processResponse(response).body())
        result.addSource(dataSource) { newData ->
            result.removeSource(dataSource)
            setValue(newData)
        }
    }

    private fun loadDataFromDB() {
        val loadFromDb = loadFromDb()
        result.addSource(loadFromDb) { newData ->
            result.removeSource(loadFromDb)
            setValue(newData)
        }
    }

    protected open fun onFetchFailed(exception: MainExceptions) {}

    fun asLiveData() = result as LiveData<ResultType>

    @WorkerThread
    protected open fun processResponse(response: Result.Success<ResultType>) =
        response.data

    @WorkerThread
    private fun saveCallResultInDB(data: ResultType?) {
        val toJson = Gson().toJson(data)
        fileManager?.let {
            it.writeFile(toJson, fileName)
        }
    }

    @MainThread
    protected fun shouldFetch(): Boolean {
        return false
    }

    @MainThread
    protected abstract fun convert(json: String): ResultType?

    @MainThread
    private fun loadFromDb(): LiveData<ResultType?> {
        val mutableLiveData = MutableLiveData<ResultType?>()
        val readFile = fileManager?.let { it.readFile(fileName) }
        if (readFile != null) {
            mutableLiveData.value = convert(readFile)
        } else {
            onFetchFailed(MainExceptions("File Not Found"))
        }
        return mutableLiveData
    }

    @MainThread
    private fun dataWrapper(data: ResultType?): LiveData<ResultType?> {
        val mutableLiveData = MutableLiveData<ResultType?>()
        mutableLiveData.value = data
        return mutableLiveData
    }

    @MainThread
    protected abstract suspend fun createCall(): suspend () -> Response<ResultType>

    protected abstract fun handleErrorResponseType(response: Response<ResultType>)
}
