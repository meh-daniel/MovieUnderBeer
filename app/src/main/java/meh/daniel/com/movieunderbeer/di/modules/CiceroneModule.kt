package meh.daniel.com.movieunderbeer.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import meh.daniel.com.movieunderbeer.presentation.navigation.common.IScreens
import meh.daniel.com.movieunderbeer.presentation.navigation.AppScreens
import javax.inject.Singleton

@Module
class CiceroneModule {

    val cicerone: Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router() = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = AppScreens()

}