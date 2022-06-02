package meh.daniel.com.movieunderbeer.di.modules

import dagger.Module
import dagger.Provides
import meh.daniel.com.movieunderbeer.domain.model.api.IDataSource
import meh.daniel.com.movieunderbeer.domain.model.repositories.IFilmRepository
import meh.daniel.com.movieunderbeer.domain.model.repositories.RetrofitFilmRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun filmRepository(
        api: IDataSource
    ): IFilmRepository = RetrofitFilmRepository(api)


}