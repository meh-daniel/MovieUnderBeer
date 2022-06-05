package meh.daniel.com.movieunderbeer.app

import android.app.Application
import meh.daniel.com.movieunderbeer.di.AppComponent
import meh.daniel.com.movieunderbeer.di.DaggerAppComponent
import meh.daniel.com.movieunderbeer.di.modules.CiceroneModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .ciceroneModule(CiceroneModule())
            .build()
    }

}