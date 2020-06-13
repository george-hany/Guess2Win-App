package com.core.data.repos

import com.core.data.base.BaseRepo
import com.core.data.network.ApiFactory
import com.core.network.NetworkFactoryInterface
import com.core.prefrence.SharedPreference

class MainRepo(
    var apiFactory: ApiFactory,
    sharedPreferences: SharedPreference,
    networkFactory: NetworkFactoryInterface
) : BaseRepo(sharedPreferences, networkFactory)
