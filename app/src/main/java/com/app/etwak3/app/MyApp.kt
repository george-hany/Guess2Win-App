package com.app.etwak3.app

import com.app.etwak3.di.allFeatures
import com.app.etwak3.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.core.data.di.dataSourceModules
import com.core.data.di.reposModules
import com.ninenox.kotlinlocalemanager.ApplicationLocale

class MyApp : ApplicationLocale() {
    companion object {
        lateinit var application: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        startKoin {
            androidContext(this@MyApp)
            val modules = ArrayList(allFeatures)
            modules.addAll(
                listOf(
                    appModule,
                    reposModules,
                    dataSourceModules
                )
            )
            modules(
                modules
            )
        }
    }
}
