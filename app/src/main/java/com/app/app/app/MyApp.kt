package com.app.app.app

import androidx.multidex.MultiDexApplication
import com.app.app.di.allFeatures
import com.app.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.core.data.di.dataSourceModules
import com.core.data.di.reposModules

class MyApp : MultiDexApplication() {
    companion object {
        lateinit var application: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
            androidContext(this@MyApp)
            val modules = ArrayList(allFeatures)
            modules.addAll(listOf(
                appModule,
                reposModules,
                dataSourceModules
            ))
            modules(
                modules
            )
        }
    }
}
