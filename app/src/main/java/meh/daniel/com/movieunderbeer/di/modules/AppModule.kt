package meh.daniel.com.movieunderbeer.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: Context) {

    @Provides
    fun app(): Context = app

}