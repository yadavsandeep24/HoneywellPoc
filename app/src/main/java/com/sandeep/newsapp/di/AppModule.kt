package com.sandeep.newsapp.di

import com.sandeep.newsapp.api.HoneywellPocService
import com.sandeep.newsapp.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideHoneywellPocService(): HoneywellPocService {
        return Retrofit.Builder()
            .baseUrl("https://hn.algolia.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(HoneywellPocService::class.java)
    }

}
