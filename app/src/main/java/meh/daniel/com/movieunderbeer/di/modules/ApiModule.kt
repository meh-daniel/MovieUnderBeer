package meh.daniel.com.movieunderbeer.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import meh.daniel.com.movieunderbeer.app.Constants
import meh.daniel.com.movieunderbeer.model.api.IDataSource
import meh.daniel.com.movieunderbeer.model.api.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = Constants.API_URL

    @Singleton
    @Provides
    fun client() = OkHttpClient.Builder().apply {
        addInterceptor(Interceptor())
    }.build()

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IDataSource::class.java)
    }

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setLenient()
        .create()

}