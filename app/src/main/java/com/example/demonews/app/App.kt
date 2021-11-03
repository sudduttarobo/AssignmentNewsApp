package com.example.demonews.app

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.example.demonews.app.di.apiModule
import com.example.demonews.app.di.netModule
import com.example.demonews.app.di.repositoryModule
import com.example.demonews.app.di.viewModelModule
import com.example.demonews.utility.InternetUtil
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    companion object {
        private val sInstance: App? = null

        @Synchronized
        fun getInstance(): App? {
            return sInstance
        }

        @JvmStatic
        fun getAppContext(): Context? {
            return sInstance?.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(viewModelModule, repositoryModule, apiModule, netModule))
        }

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        InternetUtil.init(this)

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }


}





